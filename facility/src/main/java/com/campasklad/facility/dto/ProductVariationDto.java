package com.campasklad.facility.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariationDto {

    Long id;
    Long productId;
    Long sizeId;
    Long colorId;
}
