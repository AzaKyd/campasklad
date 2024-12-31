package com.campasklad.facility.enitity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Transfers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "source_facility_id", nullable = false, foreignKey = @ForeignKey(name = "fk_transfers_source_facility"))
    Facility sourceFacility;

    @ManyToOne
    @JoinColumn(name = "destination_facility_id", nullable = false, foreignKey = @ForeignKey(name = "fk_transfers_destination_facility"))
    Facility destinationFacility;

    @Column(name = "product_variation_id", nullable = false)
    Long productVariationId;

    @Column(nullable = false)
    Long quantity;
}
