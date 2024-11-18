package com.memopet.memopet.global.common.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class LastModifiedEntity  { // DataJpaApplication파일에 @EnableJpaAuditing넣고 쓸수있다.

    @LastModifiedBy // DataJpaApplication파일에 public AuditorAware<String> auditorProvider() 넣고 쓸수있다.
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
