package com.campasklad.facility.entity.product;

import com.campasklad.facility.entity.BaseEntity;
import com.campasklad.facility.entity.Facility;
import com.campasklad.facility.entity.ProductVariation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "facility_products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FacilityProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variation_id", nullable = false)
    ProductVariation productVariation;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false, foreignKey = @ForeignKey(name = "fk_facility"))
    Facility facility;

    @Column(nullable = false)
    Long quantity;
}
