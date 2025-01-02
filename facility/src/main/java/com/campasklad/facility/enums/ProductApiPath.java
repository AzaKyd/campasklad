package com.campasklad.facility.enums;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ProductApiPath {
    BASE_URL("localhost:8080"),
    GET_PRODUCT("/get-product");
    String path;
}
