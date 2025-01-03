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
public class ProductIdsDto {
    Long productId;
    BigDecimal totalQuantity;
}
