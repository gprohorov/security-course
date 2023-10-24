package edu.pro.securitycourse.auth;

import edu.pro.securitycourse.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegistrationRequest request) {

        return null;
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userDetailsService.loadUserByUsername(request.getUsername());
        var jwt = jwtService.generateJwt(user);
        return AuthenticationResponse.builder()
                .token(jwt)
                .build();
    }


}
