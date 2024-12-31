package com.campasklad.facility.enitity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_facility_id", nullable = false, foreignKey = @ForeignKey(name = "fk_transfers_source_facility"))
    private Facility sourceFacility;

    @ManyToOne
    @JoinColumn(name = "destination_facility_id", nullable = false, foreignKey = @ForeignKey(name = "fk_transfers_destination_facility"))
    private Facility destinationFacility;

    @Column(name = "product_variation_id", nullable = false)
    private Long productVariationId;

    @Column(nullable = false)
    private Long quantity;
}
