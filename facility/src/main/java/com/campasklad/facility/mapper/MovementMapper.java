package com.campasklad.facility.mapper;

import com.campasklad.facility.entity.Facility;
import com.campasklad.facility.entity.Movement;
import com.campasklad.facility.dto.MovementDto;
import com.campasklad.facility.enums.MovementType;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper {

    public MovementDto mapInventoryMovement(Movement movement) {
        return MovementDto.builder()
                .id(movement.getId())
                .movementType(movement.getMovementType() != null ? movement.getMovementType().name() : null)
                .facilityId(movement.getFacility() != null ? movement.getFacility().getId() : null)
                .quantity(movement.getQuantity())
                .productVariationId(movement.getProductVariationId())
                .quantityBeforeMovement(movement.getQuantityBeforeMovement())
                .quantityAfterMovement(movement.getQuantityAfterMovement())
                .build();
    }

    public Movement mapInventoryMovementDto(MovementDto movementDto, Facility facility) {
        return Movement.builder()
                .id(movementDto.getId())
                .movementType(MovementType.valueOf(movementDto.getMovementType()))
                .facility(facility)
                .quantity(movementDto.getQuantity())
                .productVariationId(movementDto.getProductVariationId())
                .quantityBeforeMovement(movementDto.getQuantityBeforeMovement())
                .quantityAfterMovement(movementDto.getQuantityAfterMovement())
                .build();
    }
}
