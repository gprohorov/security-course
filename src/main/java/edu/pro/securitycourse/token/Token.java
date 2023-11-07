package edu.pro.securitycourse.token;
/*
  @author   george
  @project   security-course
  @class  Token
  @version  1.0.0 
  @since 07.11.23 - 17.38
*/

import edu.pro.securitycourse.user.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Token {
    @Id
    private String id;
    private User user;
    private String jwt;
    private TokenType type;
    private boolean revoked;
}
