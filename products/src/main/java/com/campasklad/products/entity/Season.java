package com.campasklad.products.entity;

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
@Table(name = "seasons")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Используется для AUTO_INCREMENT
    Long id;

    @Column(name = "name", nullable = false, length = 255)
    String name;
}