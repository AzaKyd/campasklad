package com.campasklad.facility.service.impl;

import com.campasklad.facility.dto.PostingDto;
import com.campasklad.facility.dto.WriteoffDto;
import com.campasklad.facility.dto.filter.PostingFilterDto;
import com.campasklad.facility.dto.filter.WriteoffFilterDto;
import com.campasklad.facility.dto.product.PostingProductDto;
import com.campasklad.facility.dto.product.WriteoffProductDto;
import com.campasklad.facility.dto.request.PostingProductRequestDto;
import com.campasklad.facility.dto.request.WriteoffProductRequestDto;
import com.campasklad.facility.entity.*;
import com.campasklad.facility.entity.product.FacilityProduct;
import com.campasklad.facility.entity.product.PostingProduct;
import com.campasklad.facility.entity.product.WriteoffProduct;
import com.campasklad.facility.enums.DocumentStatus;
import com.campasklad.facility.exception.BaseException;
import com.campasklad.facility.exception.ExceptionType;
import com.campasklad.facility.mapper.WriteoffMapper;
import com.campasklad.facility.mapper.product.WriteoffProductMapper;
import com.campasklad.facility.repository.*;
import com.campasklad.facility.repository.product.FacilityProductRepository;
import com.campasklad.facility.repository.product.WriteoffProductRepository;
import com.campasklad.facility.service.WriteoffService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WriteoffServiceImpl implements WriteoffService {

    FacilityRepository facilityRepository;
    FacilityProductRepository facilityProductRepository;
    SizeRepository sizeRepository;
    ColorRepository colorRepository;
    ProductVariationRepository productVariationRepository;
    WriteoffProductMapper writeoffProductMapper;
    WriteoffRepository writeoffRepository;
    WriteoffProductRepository writeoffProductRepository;
    WriteoffMapper writeoffMapper;

    @Override
    public void createWriteoff(WriteoffProductRequestDto writeoffProductRequestDto) {
        Facility facility = facilityRepository.findById(writeoffProductRequestDto.getWriteoffDto().getFacilityId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        Long postingId = writeoffProductRequestDto.getWriteoffDto().getId();
        DocumentStatus status = writeoffProductRequestDto.getWriteoffDto().getStatus();

        // TODO
        List<WriteoffProductDto> writeoffProductDtos = consolidateProducts(writeoffProductRequestDto.getWriteoffProductDtos());

        if (DocumentStatus.NEW.equals(status) && Objects.isNull(postingId)) {
            Writeoff newWriteoff = Writeoff.builder()
                    .status(DocumentStatus.NEW)
                    .facility(facility)
                    .build();

            List<WriteoffProduct> writeoffProducts = processCreatePostingProductDto(writeoffProductDtos);

            Writeoff savedPosting = writeoffRepository.save(newWriteoff);
            writeoffProducts.forEach(product -> product.setWriteoff(savedPosting));
            writeoffProductRepository.saveAll(writeoffProducts);
        }
    }

    @Override
    public void updateWriteoff(WriteoffProductRequestDto writeoffProductRequestDto) {

        Long writeoffId = writeoffProductRequestDto.getWriteoffDto().getId();
        Writeoff writeoff = writeoffRepository.findById(writeoffId)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        Facility facility = facilityRepository.findById(writeoff.getFacility().getId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        List<WriteoffProduct> currentWriteoffProducts = writeoffProductRepository.findAllByWriteoffById(writeoffId);

        List<WriteoffProductDto> updatedWriteoffsDto = consolidateProducts(writeoffProductRequestDto.getWriteoffProductDtos());

        Map<Long, WriteoffProduct> currentProductsMap = currentWriteoffProducts.stream()
                .collect(Collectors.toMap(
                        writeoffProduct -> writeoffProduct.getProductVariation().getId(),
                        Function.identity()
                ));

        List<WriteoffProduct> toUpdate = new ArrayList<>();
        List<WriteoffProduct> toAdd = new ArrayList<>();
        Set<Long> updatedProductVariationIds = new HashSet<>();

        for (WriteoffProductDto productDto : updatedWriteoffsDto) {
            // Извлекаем размер
            Size size = sizeRepository.findById(productDto.getSizeId())
                    .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

            // Извлекаем цвет
            Color color = colorRepository.findById(productDto.getColorId())
                    .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

            // Ищем ProductVariation или создаем новый
            ProductVariation productVariation = productVariationRepository
                    .findProductVariationByProductIdAndSizeAndColor(productDto.getProductId(), size, color)
                    .orElseGet(() -> {
                        ProductVariation newVariation = ProductVariation.builder()
                                .productId(productDto.getProductId())
                                .size(size)
                                .color(color)
                                .build();
                        return productVariationRepository.save(newVariation); // Сохраняем новый ProductVariation
                    });

            Long variationId = productVariation.getId();
            updatedProductVariationIds.add(variationId);

            if (currentProductsMap.containsKey(variationId)) {
                // Обновляем существующий PostingProduct
                WriteoffProduct existingProduct = currentProductsMap.get(variationId);
                existingProduct.setQuantity(productDto.getQuantity());
                toUpdate.add(existingProduct);
            } else {
                // Добавляем новый PostingProduct
                WriteoffProduct newProduct = WriteoffProduct.builder()
                        .writeoff(writeoff)
                        .productVariation(productVariation)
                        .quantity(productDto.getQuantity())
                        .build();
                toAdd.add(newProduct);
            }
        }

        List<WriteoffProduct> toDelete = currentWriteoffProducts.stream()
                .filter(product -> !updatedProductVariationIds.contains(product.getProductVariation().getId()))
                .toList();

        writeoffProductRepository.deleteAll(toDelete);
        writeoffProductRepository.saveAll(toAdd);
        writeoffProductRepository.saveAll(toUpdate);

        writeoff.setFacility(facility);
        writeoffRepository.save(writeoff);
    }

    @Override
    public void deleteWriteoff(Long id) {
        writeoffProductRepository.deleteAllByWriteoffById(id);
        writeoffRepository.deleteById(id);
    }

    @Override
    public void approveWriteoff(Long writeoffId) {
        Writeoff writeoff = writeoffRepository.findById(writeoffId)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        if(DocumentStatus.APPROVED.equals(writeoff.getStatus())) {
            throw new BaseException(ExceptionType.POSTING_IS_ALREADY_APPROVED);
        }

        Facility facility = facilityRepository.findById(writeoff.getFacility().getId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

        List<WriteoffProduct> writeoffProducts = writeoffProductRepository.findAllByWriteoffById(writeoffId);
        List<FacilityProduct> facilityProducts = mapWriteoffProductsToFacilityProducts(writeoffProducts, facility);

        facilityProductRepository.saveAll(facilityProducts);
    }

    @Override
    public Page<WriteoffDto> getFilteredWriteoff(WriteoffFilterDto writeoffFilterDto, Pageable pageable) {
        Specification<Writeoff> specification = WriteoffFilterDto.filterPostings(writeoffFilterDto);

        Page<Writeoff> postingPage = writeoffRepository.findWithFilter(specification, pageable);
        return postingPage.map(writeoffMapper::toDto);
    }

    public WriteoffProductRequestDto getWriteoffById(Long id) {
        Writeoff posting = writeoffRepository.findById(id)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        List<WriteoffProductDto> writeoffProductDtos = writeoffProductRepository.findAllByWriteoffById(posting.getId())
                .stream().map(writeoffProductMapper::toDto).toList();

        WriteoffProductRequestDto writeoffProductRequestDto = new WriteoffProductRequestDto();
        writeoffProductRequestDto.setWriteoffDto(writeoffMapper.toDto(posting));
        writeoffProductRequestDto.setWriteoffProductDtos(writeoffProductDtos);

        return writeoffProductRequestDto;
    }


    private List<WriteoffProduct> processCreatePostingProductDto(List<WriteoffProductDto> writeoffProductDtos){
        return writeoffProductDtos.stream()
                .map(dto -> {
                    Size size = sizeRepository.findById(dto.getSizeId())
                            .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

                    Color color = colorRepository.findById(dto.getColorId())
                            .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));

                    ProductVariation productVariation = productVariationRepository
                            .findProductVariationByProductIdAndSizeAndColor(dto.getProductId(), size, color)
                            .orElseGet(() -> {
                                ProductVariation newVariation = ProductVariation.builder()
                                        .productId(dto.getProductId())
                                        .size(size)
                                        .color(color)
                                        .build();
                                return productVariationRepository.save(newVariation); // Сохраняем новый ProductVariation
                            });

                    return writeoffProductMapper.toEntity(dto, productVariation);
                })
                .toList();
    }

    private List<FacilityProduct> mapWriteoffProductsToFacilityProducts(List<WriteoffProduct> writeoffProducts, Facility facility) {
        return writeoffProducts.stream()
                .map(postingProduct -> FacilityProduct.builder()
                        .productVariation(postingProduct.getProductVariation())
                        .facility(facility)
                        .quantity(postingProduct.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    private List<WriteoffProductDto> consolidateProducts(List<WriteoffProductDto> writeoffProductDtos) {
        Map<String, WriteoffProductDto> productMap = new HashMap<>();

        for (WriteoffProductDto dto : writeoffProductDtos) {
            String key = dto.getProductId() + "-" + dto.getSizeId() + "-" + dto.getColorId();

            if (productMap.containsKey(key)) {
                // Если объект с таким ключом уже существует, суммируем количество
                WriteoffProductDto existing = productMap.get(key);
                existing.setQuantity(existing.getQuantity() + dto.getQuantity());
            } else {
                // Если объекта нет, добавляем новый
                productMap.put(key, dto);
            }
        }

        return new ArrayList<>(productMap.values());
    }
}
