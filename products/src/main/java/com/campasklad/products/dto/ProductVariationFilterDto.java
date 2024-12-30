package com.campasklad.products.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariationFilterDto {
    Long id;
    Long productId;
    Long sizeId;
    Long colorId;
}
