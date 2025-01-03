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
@Table(name = "product_variations")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Используется для AUTO_INCREMENT
    Long id;

    @Column(name = "product_id", nullable = false)
    Long productId;

    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false) // Ссылка на таблицу sizes
    Size size;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false) // Ссылка на таблицу colors
    Color color;
}
