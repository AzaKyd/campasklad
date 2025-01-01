package com.campasklad.facility.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryMovementDto {

    Long id;
    Long facilityId;
    Long productVariationId;
    String movementType;
    Long quantity;
    Long quantityBeforeMovement;
    Long quantityAfterMovement;
}
