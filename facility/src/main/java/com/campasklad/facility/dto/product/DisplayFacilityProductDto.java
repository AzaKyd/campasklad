package com.campasklad.facility.dto.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DisplayFacilityProductDto {

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
    BigDecimal totalQuantity;
}
