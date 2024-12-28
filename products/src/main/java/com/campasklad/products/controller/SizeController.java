package com.campasklad.products.controller;

import com.campasklad.products.dto.SeasonDto;
import com.campasklad.products.dto.SizeDto;
import com.campasklad.products.service.SeasonService;
import com.campasklad.products.service.SizeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/size")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SizeController {

    SizeService sizeService;

    @GetMapping("/get-size/{id}")
    public ResponseEntity<SizeDto> getSeason(@PathVariable Long id) {
        return ResponseEntity.ok().body(sizeService.getSizeById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<SizeDto>> getSeasons() {
        return ResponseEntity.ok().body(sizeService.getAllSeasons());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createSeason(@RequestBody SizeDto sizeDto) {
        sizeService.createSize(sizeDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateSeason(@RequestBody SizeDto sizeDto) {
        sizeService.updateSize(sizeDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable Long id) {
        sizeService.deleteSize(id);
        return ResponseEntity.ok().build();
    }
}
