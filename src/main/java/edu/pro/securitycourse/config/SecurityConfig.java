package edu.pro.securitycourse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
  @author   george
  @project   security-course
  @class  SecurityConfig
  @version  1.0.0 
  @since 12.09.23 - 19.24
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(Customizer.withDefaults())
                ;
        return http.build();

    }
}
