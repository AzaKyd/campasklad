package com.campasklad.products.controller;

import com.campasklad.products.dto.CategoryDto;
import com.campasklad.products.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/category")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable long id) {
        return ResponseEntity.ok().body(categoryService.getCategoryById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@RequestBody CategoryDto categoryDto) {
        categoryService.updateCategory(categoryDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> update(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
