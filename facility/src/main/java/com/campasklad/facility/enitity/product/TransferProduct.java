package com.campasklad.facility.enitity.product;

import com.campasklad.facility.enitity.BaseEntity;
import com.campasklad.facility.enitity.Transfer;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transfer_products")
public class TransferProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_variation_id", nullable = false)
    private Long productVariationId;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "transfer_id", nullable = false)
    private Transfer transfer;

}
