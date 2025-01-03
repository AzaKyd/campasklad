package com.campasklad.facility.service.impl;

import com.campasklad.facility.dto.ProductVariationDto;
import com.campasklad.facility.entity.Color;
import com.campasklad.facility.entity.Product;
import com.campasklad.facility.entity.ProductVariation;
import com.campasklad.facility.entity.Size;
import com.campasklad.facility.exception.BaseException;
import com.campasklad.facility.exception.ExceptionType;
import com.campasklad.facility.mapper.ProductMapper;
import com.campasklad.facility.mapper.ProductVariationMapper;
import com.campasklad.facility.repository.ColorRepository;
import com.campasklad.facility.repository.ProductRepository;
import com.campasklad.facility.repository.ProductVariationRepository;
import com.campasklad.facility.repository.SizeRepository;
import com.campasklad.facility.service.ProductVariationService;
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
    ProductVariationMapper productVariationMapper;


    @Override
    public void createProductVariation(ProductVariationDto productVariationDto) {


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
