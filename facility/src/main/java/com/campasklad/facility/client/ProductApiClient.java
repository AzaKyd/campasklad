package com.campasklad.facility.client;

import com.campasklad.facility.dto.internal.ProductResponseDto;
import com.campasklad.facility.enums.ProductApiPath;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductApiClient {

    RestTemplate restTemplate;

    public ProductResponseDto getProduct(Long id) {
        ResponseEntity<ProductResponseDto> response = restTemplate.exchange(
                ProductApiPath.GET_PRODUCT.getPath().concat(String.valueOf(id)),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                ProductResponseDto.class
        );

        return response.getBody();
    }

    public List<ProductResponseDto> getProductByIds(List<Long> ids) {

        Map<String, List<Long>> requestBody = Map.of("productIds", ids);
        HttpEntity<Map<String, List<Long>>> requestEntity = new HttpEntity<>(requestBody);

        ResponseEntity<List<ProductResponseDto>> response = restTemplate.exchange(
                ProductApiPath.GET_PRODUCTS_BY_IDS.getPath(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<ProductResponseDto>>() {}
        );

        return response.getBody();
    }
}
