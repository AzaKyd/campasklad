package com.campasklad.products.mapper;

import com.campasklad.products.dto.ProductDto;
import com.campasklad.products.entity.Category;
import com.campasklad.products.entity.Product;
import com.campasklad.products.entity.Season;
import com.campasklad.products.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .barcode(product.getBarcode())
                .code(product.getCode())
                .costPrice(product.getCostPrice())
                .sellingPrice(product.getSellingPrice())
                .picturePath(product.getPicturePath())
                .description(product.getDescription())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .supplierId(product.getSupplier() != null ? product.getSupplier().getId() : null)
                .supplierId(product.getSeason() != null ? product.getSeason().getId() : null)
                .build();
    }

    public Product toEntity(ProductDto productDto, Category category, Supplier supplier, Season season) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .barcode(productDto.getBarcode())
                .code(productDto.getCode())
                .costPrice(productDto.getCostPrice())
                .sellingPrice(productDto.getSellingPrice())
                .picturePath(productDto.getPicturePath())
                .description(productDto.getDescription())
                .category(category)
                .supplier(supplier)
                .season(season)
                .build();
    }

    public Page<ProductDto> toDtoPage(Page<Product> productPage) {
        List<ProductDto> productDtoList = productPage.stream()
                .map(this::toDto)
                .toList();
        return new PageImpl<>(productDtoList, productPage.getPageable(), productPage.getTotalElements());
    }
}
