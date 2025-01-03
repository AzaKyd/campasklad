package com.campasklad.facility.controller;

import com.campasklad.facility.dto.ColorDto;
import com.campasklad.facility.entity.Color;
import com.campasklad.facility.service.ColorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/color")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorController {

    ColorService colorService;

    @GetMapping("/get-color/{id}")
    public ResponseEntity<ColorDto> getColor(@PathVariable Long id) {
        return ResponseEntity.ok().body(colorService.getCategoryById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ColorDto>> getColors() {
        return ResponseEntity.ok().body(colorService.getAllColors());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createColor(@RequestBody ColorDto colorDto) {
        colorService.createColor(colorDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateColor(@RequestBody ColorDto colorDto) {
        colorService.updateColor(colorDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable Long id) {
        colorService.deleteColor(id);
        return ResponseEntity.ok().build();
    }
}
