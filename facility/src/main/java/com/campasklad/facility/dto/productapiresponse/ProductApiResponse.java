package com.campasklad.facility.dto.productapiresponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductApiResponse {

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
