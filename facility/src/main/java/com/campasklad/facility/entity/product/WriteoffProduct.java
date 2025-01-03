package com.campasklad.facility.entity.product;

import com.campasklad.facility.entity.BaseEntity;
import com.campasklad.facility.entity.ProductVariation;
import com.campasklad.facility.entity.Writeoff;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "writeoff_products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriteoffProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variation_id", nullable = false)
    ProductVariation productVariation;

    @Column(nullable = false)
    Long quantity;

    @ManyToOne
    @JoinColumn(name = "writeoff_id", nullable = false)
    Writeoff writeoff;
}
