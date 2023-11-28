package edu.pro.securitycourse.auth;
/*
  @author   george
  @project   security-course
  @class  VerificationRequest
  @version  1.0.0
  @since 28.11.23 - 20.08
*/

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerificationRequest {

    private String username;
    private String code;
}
