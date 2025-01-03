package com.campasklad.facility.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "sizes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Size extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Используется для AUTO_INCREMENT
    Long id;

    @Column(name = "name", nullable = false, length = 50)
    String name;
}