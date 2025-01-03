package com.campasklad.facility.controller;

import com.campasklad.facility.dto.product.FacilityProductDto;
import com.campasklad.facility.service.FacilityProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facility")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FacilityProductController {

    FacilityProductService facilityProductService;

    @PostMapping("/create-facility-product")
    public ResponseEntity<Void> createFacilityProduct(@RequestBody FacilityProductDto facilityProductDto) {
        facilityProductService.createFacilityProduct(facilityProductDto);
        return ResponseEntity.ok().build();
    }
}
