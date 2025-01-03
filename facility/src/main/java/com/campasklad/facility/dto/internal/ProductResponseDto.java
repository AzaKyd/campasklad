package com.campasklad.facility.dto.internal;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDto {

    Long id;
    String name;
    String barcode;
    String code;
    BigDecimal costPrice;
    BigDecimal sellingPrice;
    String picturePath;
    String description;
    Long categoryId;
    String categoryName;
    Long supplierId;
    Long seasonId;
}
