package com.campasklad.facility.enitity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@Entity
@Table(name = "inventory_movements")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false, foreignKey = @ForeignKey(name = "fk_inventory_movements_facility"))
    private Facility facility;

    @Column(name = "product_variation_id", nullable = false)
    private Long productVariationId;

    @Column(name = "movement_type", nullable = false)
    private String movementType;

    @Column(nullable = false)
    private Long quantity;

    @Column(name = "quantity_before_movement", nullable = false)
    private Long quantityBeforeMovement;

    @Column(name = "quantity_after_movement", nullable = false)
    private Long quantityAfterMovement;
}
