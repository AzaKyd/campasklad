package com.campasklad.facility.dto.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FacilityProductDto {

    Long id;
    Long quantity;
    Long facilityId;
    Long productVariationId;
}
