package com.campasklad.products.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {

    @CreatedBy
    @Column(nullable = false, updatable = false)
    Long createdBy;

    @LastModifiedBy
    Long updatedBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    LocalDateTime createdAt;

    @LastModifiedDate
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    LocalDateTime updatedAt;

}
