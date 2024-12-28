package com.campasklad.products.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Synchronize;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "sizes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Используется для AUTO_INCREMENT
    Long id;

    @Column(name = "name", nullable = false, length = 50)
    String name;
}