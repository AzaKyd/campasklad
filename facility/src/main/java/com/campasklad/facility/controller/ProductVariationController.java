package com.campasklad.facility.controller;

import com.campasklad.facility.dto.ProductVariationDto;
import com.campasklad.facility.service.ProductVariationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-variation")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductVariationController {

    ProductVariationService productVariationService;

    @GetMapping("/get-product-variation/{id}")
    public ResponseEntity<ProductVariationDto> getProductVariation(@PathVariable Long id) {
        return ResponseEntity.ok().body(productVariationService.getProductVariationById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createProductVariation(@RequestBody ProductVariationDto productVariationDto) {
        productVariationService.createProductVariation(productVariationDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateProductVariation(@RequestBody ProductVariationDto productVariationDto) {
        productVariationService.updateProductVariation(productVariationDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProductVariation(@PathVariable Long id) {
        productVariationService.deleteProductVariation(id);
        return ResponseEntity.ok().build();
    }
}
