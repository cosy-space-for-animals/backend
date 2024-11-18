package com.memopet.memopet.global.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class FirstCreatedEntity extends LastModifiedEntity { // DataJpaApplication파일에 @EnableJpaAuditing넣고 쓸수있다.

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

}
