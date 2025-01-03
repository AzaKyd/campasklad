package com.campasklad.facility.entity.product;

import com.campasklad.facility.entity.BaseEntity;
import com.campasklad.facility.entity.ProductVariation;
import com.campasklad.facility.entity.Transfer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transfer_products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variation_id", nullable = false)
    ProductVariation productVariation;

    @Column(nullable = false)
    Long quantity;

    @ManyToOne
    @JoinColumn(name = "transfer_id", nullable = false)
    Transfer transfer;

}
