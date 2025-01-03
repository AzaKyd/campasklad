package com.campasklad.products.controller.internal;

import com.campasklad.products.dto.ProductDto;
import com.campasklad.products.dto.internal.ProductRequestDto;
import com.campasklad.products.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExternalProductController {

    ProductService productService;

    @GetMapping("/get-facility-products")
    public ResponseEntity<List<ProductDto>> getFacilityProducts(@RequestBody ProductRequestDto productRequestDto) {
        List<ProductDto> products = productService.getProductsByIds(productRequestDto.getProductIds());
        return ResponseEntity.ok().body(products);
    }
}
