package com.campasklad.facility.dto.request;

import com.campasklad.facility.dto.PostingDto;
import com.campasklad.facility.dto.product.PostingProductDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostingProductRequestDto {

    PostingDto postingDto;
    List<PostingProductDto> postingProductDtos;
}
