package edu.pro.securitycourse.audit;
/*
  @author   george
  @project   security-course
  @class  AuditorConfig
  @version  1.0.0 
  @since 19.11.23 - 15.22
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class AuditionConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
