package edu.pro.securitycourse.auth;

import edu.pro.securitycourse.jwt.JwtService;
import edu.pro.securitycourse.tfa.TfaService;
import edu.pro.securitycourse.token.Token;
import edu.pro.securitycourse.token.TokenService;
import edu.pro.securitycourse.token.TokenType;
import edu.pro.securitycourse.user.Role;
import edu.pro.securitycourse.user.User;
import edu.pro.securitycourse.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

/*
  @author   george
  @project   security-course
  @class  AuthenticationService
  @version  1.0.0 
  @since 16.10.23 - 22.09
*/
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final TfaService tfaService;

    public AuthenticationResponse register(RegistrationRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.ROLE_USER)
                .tfaEnabled(request.isTfaEnabled())
                .build();
        if (request.isTfaEnabled()) {
            user.setSecretWord(tfaService.generateNewSecret());
        }
        User userRecorded = userService.create(user);
        if (userRecorded == null) {
            return AuthenticationResponse.builder()
                    .token(" Persistence failed ")
                    .build();
        }
        String jwt = jwtService.generateJwt(user);
        Token token = Token.builder()
                .user(userRecorded)
                .jwt(jwt)
                .type(TokenType.BEARER)
                .revoked(false)
                .build();
        tokenService.record(token);
        return AuthenticationResponse.builder()
                .token(jwt)
                .tfaEnabled(userRecorded.isTfaEnabled())
                .qrImageUri(tfaService.generateQrCodeImageUri(user.getSecretWord()))
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User userExtracted = userService.getUserByName(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateJwt(userExtracted);
        Token token = Token.builder()
                .user(userExtracted)
                .jwt(jwt)
                .type(TokenType.BEARER)
                .revoked(false)
                .build();
        revokeAllTokensByUser(userExtracted);
        tokenService.record(token);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }

    private void revokeAllTokensByUser(User user) {
        List<Token> allValidTokensByUser = tokenService.getAllValidTokensByUser(user);
        if (allValidTokensByUser.isEmpty()) {
            return;
        }
        allValidTokensByUser.forEach(token -> token.setRevoked(true));
        tokenService.recordAll(allValidTokensByUser);
    }
    public AuthenticationResponse verifyCode(
            VerificationRequest verificationRequest
    ) {
        User user = userService
                .getUserByName(verificationRequest.getUsername())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No user found with %S", verificationRequest.getUsername()))
                );
        if (tfaService.isOtpNotValid(user.getSecretWord(), verificationRequest.getCode())) {

            throw new BadCredentialsException("Code is not correct");
        }
        var jwtToken = jwtService.generateJwt(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .tfaEnabled(user.isTfaEnabled())
                .build();
    }

}
