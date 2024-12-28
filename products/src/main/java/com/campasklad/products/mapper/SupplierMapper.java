package com.campasklad.products.mapper;

import com.campasklad.products.dto.SupplierDto;
import com.campasklad.products.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public SupplierDto toDto(Supplier supplier) {
        return SupplierDto.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .phone(supplier.getPhone())
                .socialLink(supplier.getSocialLink())
                .location(supplier.getLocation())
                .aisleNumber(supplier.getAisleNumber())
                .containerNumber(supplier.getContainerNumber())
                .build();
    }

    public Supplier toEntity(SupplierDto supplierDto) {
        return Supplier.builder()
                .id(supplierDto.getId())
                .name(supplierDto.getName())
                .phone(supplierDto.getPhone())
                .socialLink(supplierDto.getSocialLink())
                .location(supplierDto.getLocation())
                .aisleNumber(supplierDto.getAisleNumber())
                .containerNumber(supplierDto.getContainerNumber())
                .build();
    }
}
