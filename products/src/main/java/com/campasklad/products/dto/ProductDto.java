package com.campasklad.products.dto;

import com.campasklad.products.entity.Category;
import com.campasklad.products.entity.Season;
import com.campasklad.products.entity.Supplier;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {

    Long id;
    String name;
    String barcode;
    String code;
    BigDecimal costPrice;
    BigDecimal sellingPrice;
    String picturePath;
    String description;
    Long categoryId;
    Long supplierId;
    Long seasonId;
}
