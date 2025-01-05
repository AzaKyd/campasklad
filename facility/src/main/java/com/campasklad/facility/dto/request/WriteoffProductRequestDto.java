package com.campasklad.facility.dto.request;

import com.campasklad.facility.dto.WriteoffDto;
import com.campasklad.facility.dto.product.WriteoffProductDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriteoffProductRequestDto {

    WriteoffDto writeoffDto;
    List<WriteoffProductDto> writeoffProductDtos;
}
