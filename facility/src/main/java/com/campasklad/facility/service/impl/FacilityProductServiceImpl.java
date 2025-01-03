package com.campasklad.facility.service.impl;

import com.campasklad.facility.client.ProductApiClient;
import com.campasklad.facility.dto.internal.ProductResponseDto;
import com.campasklad.facility.dto.product.DisplayFacilityProductDto;
import com.campasklad.facility.dto.product.FacilityProductDto;
import com.campasklad.facility.dto.internal.ProductIdsDto;
import com.campasklad.facility.entity.Facility;
import com.campasklad.facility.exception.BaseException;
import com.campasklad.facility.exception.ExceptionType;
import com.campasklad.facility.mapper.product.FacilityProductMapper;
import com.campasklad.facility.repository.FaciltiyRepository;
import com.campasklad.facility.repository.product.FacilityProductRepository;
import com.campasklad.facility.service.FacilityProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FacilityProductServiceImpl implements FacilityProductService {

    FacilityProductRepository facilityProductRepository;
    FaciltiyRepository faciltiyRepository;
    FacilityProductMapper facilityProductMapper;
    ProductApiClient productApiClient;


    @Override
    public void createFacilityProduct(FacilityProductDto facilityProductDto) {
        Facility facility = faciltiyRepository.findById(facilityProductDto.getFacilityId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        facilityProductRepository.save(facilityProductMapper.toEntity(facilityProductDto, facility));
    }

    @Override
    public Page<DisplayFacilityProductDto> getFacilityProducts(Long facilityId, Pageable pageable) {
        List<ProductIdsDto> facilityProductDtoList = facilityProductRepository.findGroupedProducts(facilityId, pageable);
        List<Long> idsForProductRequest = facilityProductDtoList.stream().map(ProductIdsDto::getProductId).toList();
        List<ProductResponseDto> productResponseDtos = productApiClient.getProductByIds(idsForProductRequest);


        Map<Long, ProductResponseDto> responseProductMap = productResponseDtos.stream()
                .collect(Collectors.toMap(ProductResponseDto::getId, dto -> dto));

        List<DisplayFacilityProductDto> facilityProductDtos = facilityProductDtoList.stream()
                .map(productIdsDto -> {
                    // Ищем соответствующий ProductResponseDto по productId
                    ProductResponseDto productResponseDto = responseProductMap.get(productIdsDto.getProductId());

                    if (productResponseDto != null) {
                        // Создаём FacilityProductDto, объединяя данные из обоих DTO
                        return facilityProductMapper.toDisplayDto(productResponseDto, productIdsDto);
                    }
                    return null; // Если соответствия нет
                })
                .filter(Objects::nonNull) // Исключаем null-объекты
                .toList();

        return new PageImpl<>(facilityProductDtos, pageable, facilityProductDtoList.size());
    }
}
