package edu.pro.securitycourse.user;
/*
  @author   george
  @project   security-course
  @class  UserService
  @version  1.0.0 
  @since 03.11.23 - 19.08
*/

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User create(User user){
        if (isUsernamePresent(user.getUsername())) {
            return null;
        }
        return repository.save(user);
    }

    public Optional<User> getUserByName(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    private boolean isUsernamePresent(String username) {
        return this.getUserByName(username).isPresent();
    }
}
