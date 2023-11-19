package edu.pro.securitycourse.model;
/*
  @author   george
  @project   security-course
  @class  AuditorAwareImpl
  @version  1.0.0 
  @since 19.11.23 - 13.11
*/

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.empty();
    }
}
