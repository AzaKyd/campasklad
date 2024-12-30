package com.campasklad.products.controller;

import com.campasklad.products.dto.ProductDto;
import com.campasklad.products.dto.ProductFilterDto;
import com.campasklad.products.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                                           @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(productService.getFilteredProducts(productFilterDto, pageable));
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
