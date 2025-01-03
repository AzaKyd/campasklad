package com.campasklad.facility.controller;

import com.campasklad.facility.client.ProductApiClient;
import com.campasklad.facility.dto.FacilityDto;
import com.campasklad.facility.service.FacilityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facility")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FacilityController {

    FacilityService facilityService;

    @GetMapping("/get-facility/{id}")
    public ResponseEntity<FacilityDto> getFacility(@PathVariable Long id) {
        return ResponseEntity.ok().body(facilityService.getFacilityById(id));
    }

    @PostMapping("/create-facility")
    public ResponseEntity<Void> createFacility(@RequestBody FacilityDto facilityDto) {
        facilityService.createFacility(facilityDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-facility")
    public ResponseEntity<Void> updateFacility(@RequestBody FacilityDto facilityDto) {
        facilityService.updateFacility(facilityDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete-facility/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable Long id) {
        facilityService.deleteFacility(id);
        return ResponseEntity.ok().build();
    }
}
