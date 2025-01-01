package com.campasklad.facility.dto.product;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostingProductDto {

    Long id;
    Long productVariationId;
    Long quantity;
    Long postingId;
}
