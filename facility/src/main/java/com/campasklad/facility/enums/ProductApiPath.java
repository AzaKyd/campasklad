package com.campasklad.facility.enums;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ProductApiPath {
    BASE_URL("http://localhost:8080"),
    GET_PRODUCT("/get-product");
    String path;
}
