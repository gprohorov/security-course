package edu.pro.securitycourse.jwt;
/*
  @author   george
  @project   security-course
  @class  JwtService
  @version  1.0.0 
  @since 23.10.23 - 21.24
*/

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET_KEY
            = "99999029f3854e50d82c7a7bd9c8440e6c13c380d8a50d9fd16789605fb562e4";

    private Key getSignedKey() {
        byte[] keyInBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyInBytes);
    }

    public String generateJwt(UserDetails user) {
        return this.generateJwt(new HashMap<>(), user);
    }

    public String generateJwt(Map<String, Object> additionalClaims, UserDetails user) {
        return Jwts.builder()
                .setClaims(additionalClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();

    }



}
