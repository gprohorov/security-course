package edu.pro.securitycourse.audit;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/*
  @author   george
  @project   security-course
  @class  AuditMetadata
  @version  1.0.0 
  @since 19.11.23 - 15.18
*/
@Data
public abstract class AuditMetadata {
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private  LocalDateTime updatedAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
}
