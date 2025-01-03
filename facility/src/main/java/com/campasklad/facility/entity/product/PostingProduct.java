package com.campasklad.facility.entity.product;

import com.campasklad.facility.entity.BaseEntity;
import com.campasklad.facility.entity.Posting;
import com.campasklad.facility.entity.ProductVariation;
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
@Table(name = "posting_products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostingProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variation_id", nullable = false)
    private ProductVariation productVariation;

    @Column(nullable = false)
    Long quantity;

    @ManyToOne
    @JoinColumn(name = "posting_id", nullable = false)
    Posting posting;
}
