package com.campasklad.facility.enitity.product;

import com.campasklad.facility.enitity.BaseEntity;
import com.campasklad.facility.enitity.Writeoff;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "writeoff_products")
public class WriteoffProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_variation_id", nullable = false)
    private Long productVariationId;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "writeoff_id", nullable = false)
    private Writeoff writeoff;
}
