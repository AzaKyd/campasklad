package com.campasklad.facility.dto;

import com.campasklad.facility.enitity.Facility;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriteoffDto {

    Long id;
    Long productVariationId;
    Long quantity;
    Facility facility;
}
