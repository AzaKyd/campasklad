package com.campasklad.facility.enitity.product;

import com.campasklad.facility.enitity.BaseEntity;
import com.campasklad.facility.enitity.Facility;
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

    @Column(name = "product_variation_id", nullable = false)
    Long productVariationId;

    @Column(name = "product_id", nullable = false)
    Long productId;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false, foreignKey = @ForeignKey(name = "fk_facility"))
    Facility facility;

    @Column(nullable = false)
    Long quantity;
}
