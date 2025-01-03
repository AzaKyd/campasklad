package com.campasklad.products.service;

import com.campasklad.products.dto.SupplierDto;

import java.util.List;

public interface SupplierService {

    void createSupplier(SupplierDto supplierDto);
    SupplierDto getSupplierById(Long id);
    List<SupplierDto> getAllSuppliers();
    void updateSupplier(SupplierDto supplierDto);
    void deleteSupplier(Long id);
}
