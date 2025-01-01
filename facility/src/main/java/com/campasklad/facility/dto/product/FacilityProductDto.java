package com.campasklad.facility.dto.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FacilityProductDto {

    Long id;
    Long productVariationId;
    Long productId;
    Long facilityId;
    Long quantity;
}
