package edu.pro.securitycourse.token;
/*
  @author   george
  @project   security-course
  @class  TokenRepository
  @version  1.0.0 
  @since 07.11.23 - 17.46
*/

import edu.pro.securitycourse.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<Token, String> {
    List<Token> findTokensByUser(User user);
    Optional<Token> findByJwt(String jwt);
}
