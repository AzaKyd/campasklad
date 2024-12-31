package com.campasklad.facility.mapper;

import com.campasklad.facility.dto.InventoryDto;
import com.campasklad.facility.enitity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryDto toDto(Inventory inventory) {
        return InventoryDto.builder()
                .id(inventory.getId())
                .productId(inventory.getProductId())
                .quantity(inventory.getQuantity())
                .productVariationId(inventory.getProductVariationId())
                .facility(inventory.getFacility())
                .build();
    }

    public Inventory toEntity(InventoryDto inventoryDto) {
        return Inventory.builder()
                .id(inventoryDto.getId())
                .productId(inventoryDto.getProductId())
                .quantity(inventoryDto.getQuantity())
                .productVariationId(inventoryDto.getProductVariationId())
                .facility(inventoryDto.getFacility().)
                .build();
    }
}
