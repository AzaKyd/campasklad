package com.campasklad.facility.dto.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriteoffProductDto {

    Long id;
    Long productId;
    Long productVariationId;
    Long sizeId;
    Long colorId;
    Long quantity;
    Long writeoffId;
}
