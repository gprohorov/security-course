package edu.pro.securitycourse.user;
/*
  @author   george
  @project   security-course
  @class  UserRepository
  @version  1.0.0 
  @since 03.11.23 - 19.07
*/

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
