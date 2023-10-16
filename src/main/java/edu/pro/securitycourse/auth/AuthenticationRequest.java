package edu.pro.securitycourse.auth;
/*
  @author   george
  @project   security-course
  @class  AuthenticationRequest
  @version  1.0.0 
  @since 16.10.23 - 22.14
*/


import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class AuthenticationRequest {
    private String username;
    private String password;
}
