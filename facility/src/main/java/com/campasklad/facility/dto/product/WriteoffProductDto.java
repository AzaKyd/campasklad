package com.campasklad.facility.dto.product;

import com.campasklad.facility.enitity.Writeoff;
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
    Long productVariationId;
    Long quantity;
    Long writeoff;
}
