package com.campasklad.products.service.impl;

import com.campasklad.products.dto.ProductVariationDto;
import com.campasklad.products.entity.Color;
import com.campasklad.products.entity.Product;
import com.campasklad.products.entity.ProductVariation;
import com.campasklad.products.entity.Size;
import com.campasklad.products.exception.BaseException;
import com.campasklad.products.exception.ExceptionType;
import com.campasklad.products.mapper.ProductMapper;
import com.campasklad.products.mapper.ProductVariationMapper;
import com.campasklad.products.repository.ColorRepository;
import com.campasklad.products.repository.ProductRepository;
import com.campasklad.products.repository.ProductVariationRepository;
import com.campasklad.products.repository.SizeRepository;
import com.campasklad.products.service.ProductVariationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductVariationServiceImpl implements ProductVariationService {

    ProductVariationRepository productVariationRepository;
    SizeRepository sizeRepository;
    ColorRepository colorRepository;
    ProductRepository productRepository;
    ProductVariationMapper productVariationMapper;
    private final ProductMapper productMapper;


    @Override
    public void createProductVariation(ProductVariationDto productVariationDto) {
        Product product = productRepository.findById(productVariationDto.getProductId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        Size size = sizeRepository.findById(productVariationDto.getSizeId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        Color color = colorRepository.findById(productVariationDto.getColorId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        productVariationRepository.save(productVariationMapper.toEntity(
                productVariationDto,
                product,
                size,
                color
                )
        );
    }

    @Override
    public ProductVariationDto getProductVariationById(Long id) {
        ProductVariation productVariation = productVariationRepository.findById(id)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        return productVariationMapper.toDto(productVariation);
    }

    @Override
    public List<ProductVariationDto> getProductsByProductVariations() {
        return null;
    }

    @Override
    public void updateProductVariation(ProductVariationDto productVariationDto) {
        ProductVariation productVariation = productVariationRepository.findById(productVariationDto.getId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        Product product = productRepository.findById(productVariationDto.getProductId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        Size size = sizeRepository.findById(productVariationDto.getSizeId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        Color color = colorRepository.findById(productVariationDto.getColorId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        productVariation.setProduct(product);
        productVariation.setSize(size);
        productVariation.setColor(color);

        productVariationRepository.save(productVariation);
    }

    @Override
    public void deleteProductVariation(Long id) {
        if (!productVariationRepository.existsById(id)) {
            throw new BaseException(ExceptionType.ENTITY_NOT_FOUND);
        }
        productVariationRepository.deleteById(id);
    }
}
