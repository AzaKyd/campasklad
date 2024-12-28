package com.campasklad.products.controller;

import com.campasklad.products.dto.ColorDto;
import com.campasklad.products.dto.SeasonDto;
import com.campasklad.products.service.ColorService;
import com.campasklad.products.service.SeasonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/season")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SeasonController {

    SeasonService seasonService;

    @GetMapping("/get-season/{id}")
    public ResponseEntity<SeasonDto> getSeason(@PathVariable Long id) {
        return ResponseEntity.ok().body(seasonService.getSeasonById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<SeasonDto>> getSeasons() {
        return ResponseEntity.ok().body(seasonService.getAllSeasons());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createSeason(@RequestBody SeasonDto seasonDto) {
        seasonService.createSeason(seasonDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateSeason(@RequestBody SeasonDto seasonDto) {
        seasonService.updateSeason(seasonDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable Long id) {
        seasonService.deleteSeason(id);
        return ResponseEntity.ok().build();
    }
}
