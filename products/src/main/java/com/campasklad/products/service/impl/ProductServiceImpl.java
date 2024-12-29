package com.campasklad.products.service.impl;

import com.campasklad.products.dto.ProductDto;
import com.campasklad.products.dto.ProductFilterDto;
import com.campasklad.products.entity.Category;
import com.campasklad.products.entity.Product;
import com.campasklad.products.entity.Season;
import com.campasklad.products.entity.Supplier;
import com.campasklad.products.exception.BaseException;
import com.campasklad.products.exception.ExceptionType;
import com.campasklad.products.mapper.ProductMapper;
import com.campasklad.products.repository.CategoryRepository;
import com.campasklad.products.repository.ProductRepository;
import com.campasklad.products.repository.SeasonRepository;
import com.campasklad.products.repository.SupplierRepository;
import com.campasklad.products.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    SupplierRepository supplierRepository;
    SeasonRepository seasonRepository;
    ProductMapper productMapper;

    @Override
    public void createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        Supplier supplier = Optional.ofNullable(productDto.getSupplierId())
                .flatMap(supplierRepository::findById)
                .orElse(null);

        Season season = Optional.ofNullable(productDto.getSeasonId())
                .flatMap(seasonRepository::findById)
                .orElse(null);

        Product product = Product.builder()
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

        productRepository.save(product);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        return productMapper.toDto(product);
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        Supplier supplier = Optional.ofNullable(productDto.getSupplierId())
                .flatMap(supplierRepository::findById)
                .orElse(null);

        Season season = Optional.ofNullable(productDto.getSeasonId())
                .flatMap(seasonRepository::findById)
                .orElse(null);

        product.setName(productDto.getName());
        product.setBarcode(productDto.getBarcode());
        product.setCode(productDto.getCode());
        product.setCostPrice(productDto.getCostPrice());
        product.setSellingPrice(productDto.getSellingPrice());
        product.setPicturePath(productDto.getPicturePath());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);
        product.setSupplier(supplier);
        product.setSeason(season);

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new BaseException(ExceptionType.ENTITY_NOT_FOUND);
        }
        productRepository.deleteById(id);
    }

    public Page<ProductDto> getFilteredProducts(ProductFilterDto productFilterDto, Pageable pageable) {

        Specification<Product> specification = ProductFilterDto.filterProducts(productFilterDto);

        Page<Product> productPage = productRepository.findAll(specification, pageable);

        List<ProductDto> productDtoList = productPage.stream()
                .map(productMapper::toDto)
                .toList();
        return new PageImpl<>(productDtoList, pageable, productPage.getTotalElements());
    }

}
