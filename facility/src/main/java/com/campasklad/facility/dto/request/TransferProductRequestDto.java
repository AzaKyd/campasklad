package com.campasklad.facility.dto.request;

import com.campasklad.facility.dto.TransferDto;
import com.campasklad.facility.dto.product.PostingProductDto;
import com.campasklad.facility.dto.product.TransferProductDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferProductRequestDto {

    TransferDto transferDto;
    List<TransferProductDto> transferProductDtos;
}