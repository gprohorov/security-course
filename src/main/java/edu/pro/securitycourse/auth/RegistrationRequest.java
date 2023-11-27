package edu.pro.securitycourse.auth;
/*
  @author   george
  @project   security-course
  @class  RegistrationRequest
  @version  1.0.0 
  @since 16.10.23 - 22.01
*/

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class RegistrationRequest {
    private String username;
    private String password;
    private String email;
    private boolean tfaEnabled;
}
