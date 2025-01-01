package com.campasklad.facility.mapper;

import com.campasklad.facility.enitity.Facility;
import com.campasklad.facility.enitity.InventoryMovement;
import com.campasklad.facility.dto.InventoryMovementDto;
import com.campasklad.facility.enums.InventoryMovementType;
import org.springframework.stereotype.Component;

@Component
public class InventoryMovementMapper {

    public InventoryMovementDto mapInventoryMovement(InventoryMovement inventoryMovement) {
        return InventoryMovementDto.builder()
                .id(inventoryMovement.getId())
                .movementType(inventoryMovement.getMovementType() != null ? inventoryMovement.getMovementType().name() : null)
                .facilityId(inventoryMovement.getFacility() != null ? inventoryMovement.getFacility().getId() : null)
                .quantity(inventoryMovement.getQuantity())
                .productVariationId(inventoryMovement.getProductVariationId())
                .quantityBeforeMovement(inventoryMovement.getQuantityBeforeMovement())
                .quantityAfterMovement(inventoryMovement.getQuantityAfterMovement())
                .build();
    }

    public InventoryMovement mapInventoryMovementDto(InventoryMovementDto inventoryMovementDto, Facility facility) {
        return InventoryMovement.builder()
                .id(inventoryMovementDto.getId())
                .movementType(InventoryMovementType.valueOf(inventoryMovementDto.getMovementType()))
                .facility(facility)
                .quantity(inventoryMovementDto.getQuantity())
                .productVariationId(inventoryMovementDto.getProductVariationId())
                .quantityBeforeMovement(inventoryMovementDto.getQuantityBeforeMovement())
                .quantityAfterMovement(inventoryMovementDto.getQuantityAfterMovement())
                .build();
    }
}
