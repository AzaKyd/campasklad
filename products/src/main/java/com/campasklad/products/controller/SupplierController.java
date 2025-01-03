package com.campasklad.products.controller;


import com.campasklad.products.dto.SupplierDto;
import com.campasklad.products.service.SupplierService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/supplier")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SupplierController {

    SupplierService supplierService;

    @GetMapping("/get-supplier/{id}")
    public ResponseEntity<SupplierDto> getSeason(@PathVariable Long id) {
        return ResponseEntity.ok().body(supplierService.getSupplierById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<SupplierDto>> getSeasons() {
        return ResponseEntity.ok().body(supplierService.getAllSuppliers());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createSeason(@RequestBody SupplierDto supplierDto) {
        supplierService.createSupplier(supplierDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateSeason(@RequestBody SupplierDto supplierDto) {
        supplierService.updateSupplier(supplierDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok().build();
    }
}
