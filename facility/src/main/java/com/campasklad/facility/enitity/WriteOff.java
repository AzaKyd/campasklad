package com.campasklad.facility.enitity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "writeoffs")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriteOff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "product_variation_id", nullable = false)
    Long productVariationId;

    @Column(nullable = false)
    Long quantity;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false, foreignKey = @ForeignKey(name = "fk_writeoffs_facility"))
    Facility facility;
}
