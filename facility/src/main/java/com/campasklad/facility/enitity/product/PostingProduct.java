package com.campasklad.facility.enitity.product;

import com.campasklad.facility.enitity.BaseEntity;
import com.campasklad.facility.enitity.Posting;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posting_products")
public class PostingProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_variation_id", nullable = false)
    private Long productVariationId;

    @Column(nullable = false)
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "posting_id", nullable = false)
    private Posting posting;
}
