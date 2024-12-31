package com.campasklad.facility.dto;

import com.campasklad.facility.enitity.Facility;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InventoryMovermentDto {

    Long id;
    Facility facility;
    Long productVariationId;
    String movementType;
    Long quantity;
    Long quantityBeforeMovement;
    Long quantityAfterMovement;
}
