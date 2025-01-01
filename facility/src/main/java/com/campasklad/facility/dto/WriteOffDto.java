package com.campasklad.facility.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriteOffDto {

    Long id;
    Long productVariationId;
    Long quantity;
    Long facilityId;
}
