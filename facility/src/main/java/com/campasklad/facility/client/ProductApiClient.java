package com.campasklad.facility.client;

import com.campasklad.facility.dto.productapiresponse.ProductApiResponse;
import com.campasklad.facility.enums.ProductApiPath;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductApiClient {

    RestTemplate restTemplate;

    public ProductApiResponse getProduct(Long id) {
        ResponseEntity<ProductApiResponse> response = restTemplate.exchange(
                ProductApiPath.GET_PRODUCT.getPath().concat(String.valueOf(id)),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                ProductApiResponse.class
        );

        return response.getBody();
    }
}
