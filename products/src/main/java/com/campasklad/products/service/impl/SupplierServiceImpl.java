package com.campasklad.products.service.impl;

import com.campasklad.products.dto.SupplierDto;
import com.campasklad.products.entity.Supplier;
import com.campasklad.products.exception.BaseException;
import com.campasklad.products.exception.ExceptionType;
import com.campasklad.products.mapper.SupplierMapper;
import com.campasklad.products.repository.SupplierRepository;
import com.campasklad.products.service.SupplierService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SupplierServiceImpl implements SupplierService {

    SupplierRepository supplierRepository;
    SupplierMapper supplierMapper;

    @Override
    public void createSupplier(SupplierDto supplierDto) {
        supplierRepository.save(supplierMapper.toEntity(supplierDto));
    }

    @Override
    public SupplierDto getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        return supplierMapper.toDto(supplier);
    }

    @Override
    public List<SupplierDto> getAllSuppliers() {
        return supplierRepository.findAll().stream().map(supplierMapper::toDto).toList();
    }

    @Override
    public void updateSupplier(SupplierDto supplierDto) {
        Supplier supplier = supplierRepository.findById(supplierDto.getId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        supplier.setName(supplierDto.getName());
        supplier.setPhone(supplierDto.getPhone());
        supplier.setSocialLink(supplierDto.getSocialLink());
        supplier.setLocation(supplierDto.getLocation());
        supplier.setAisleNumber(supplierDto.getAisleNumber());
        supplier.setContainerNumber(supplierDto.getContainerNumber());

        supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        if(!supplierRepository.existsById(id)) {
            throw new BaseException(ExceptionType.ENTITY_NOT_FOUND);
        }
        supplierRepository.deleteById(id);
    }
}
