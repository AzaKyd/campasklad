package com.campasklad.products.controller;

import com.campasklad.products.dto.ProductDto;
import com.campasklad.products.dto.ProductFilterDto;
import com.campasklad.products.dto.SeasonDto;
import com.campasklad.products.entity.Product;
import com.campasklad.products.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {

    ProductService productService;

    @GetMapping("/get-product/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @GetMapping("/filter-products")
    public ResponseEntity<Page<ProductDto>> filterProducts(@RequestBody ProductFilterDto productFilterDto,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size,
                                                           @RequestParam(defaultValue = "name") String sortByName,
                                                           @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortByName).ascending() : Sort.by(sortByName).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        productService.getFilteredProducts(productFilterDto, pageable);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createSeason(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateSeason(@RequestBody ProductDto productDto) {
        productService.updateProduct(productDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
