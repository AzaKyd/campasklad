package com.campasklad.facility.dto.product;

import com.campasklad.facility.utils.ProductAttributes;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostingProductDto implements ProductAttributes {

    Long id;
    Long productId;
    Long productVariationId;
    Long sizeId;
    Long colorId;
    Long quantity;
    Long postingId;
}
