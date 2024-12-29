package com.campasklad.products.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Используется для AUTO_INCREMENT
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "barcode", nullable = false, length = 50)
    String barcode;

    @Column(name = "code", nullable = false, length = 50)
    String code;

    @Column(name = "cost_price", nullable = false, precision = 38, scale = 2)
    BigDecimal costPrice;

    @Column(name = "selling_price", nullable = false, precision = 38, scale = 2)
    BigDecimal sellingPrice;

    @Column(name = "picture_path")
    String picturePath;

    @Column(name = "description")
    String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "season_id")
    Season season;
}
