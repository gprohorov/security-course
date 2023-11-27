package edu.pro.securitycourse.auth;
/*
  @author   george
  @project   security-course
  @class  AuthenticationResponse
  @version  1.0.0 
  @since 16.10.23 - 22.06
*/

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthenticationResponse {
    private String token;
    private boolean tfaEnabled;
    private String qrImageUri;
}
