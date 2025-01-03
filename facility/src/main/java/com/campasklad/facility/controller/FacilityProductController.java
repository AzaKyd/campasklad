package com.campasklad.facility.controller;

import com.campasklad.facility.dto.product.DisplayFacilityProductDto;
import com.campasklad.facility.dto.product.FacilityProductDto;
import com.campasklad.facility.service.FacilityProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facility")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FacilityProductController {

    FacilityProductService facilityProductService;

    @GetMapping("/get-facility-products/{facilityId}")
    public ResponseEntity<Page<DisplayFacilityProductDto>> getFacilityProducts(@PathVariable Long facilityId, @PageableDefault Pageable pageable) {
        facilityProductService.getFacilityProducts(facilityId, pageable);
        return ResponseEntity.ok().body(facilityProductService.getFacilityProducts(facilityId, pageable));
    }

    @PostMapping("/create-facility-product")
    public ResponseEntity<Void> createFacilityProduct(@RequestBody FacilityProductDto facilityProductDto) {
        facilityProductService.createFacilityProduct(facilityProductDto);
        return ResponseEntity.ok().build();
    }
}
