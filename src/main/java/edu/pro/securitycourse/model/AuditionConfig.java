package edu.pro.securitycourse.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/*
  @author   george
  @project   security-course
  @class  AuditionConfig
  @version  1.0.0 
  @since 19.11.23 - 13.16
*/
@Configuration
@EnableMongoAuditing
public class AuditionConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
