package com.campasklad.facility.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "colors")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Color extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Используется для AUTO_INCREMENT
    Long id;

    @Column(name = "name", nullable = false, length = 50)
    String name;
}
