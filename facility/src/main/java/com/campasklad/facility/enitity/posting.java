package com.campasklad.facility.enitity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "postings")
public class posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_variation_id", nullable = false)
    private Long productVariationId;

    @Column(nullable = false)
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false, foreignKey = @ForeignKey(name = "fk_postings_facility"))
    private Facility facility;
}
