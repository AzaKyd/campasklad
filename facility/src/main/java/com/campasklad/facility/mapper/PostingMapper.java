package com.campasklad.facility.mapper;

import com.campasklad.facility.dto.PostingDto;
import com.campasklad.facility.enitity.Facility;
import com.campasklad.facility.enitity.Posting;

public class PostingMapper {

    public PostingDto toPostinDto(Posting posting) {
        return PostingDto.builder()
                .id(posting.getId())
                .facilityId(posting.getFacility() != null ? posting.getFacility().getId() : null)
                .productVariationId(posting.getProductVariationId())
                .quantity(posting.getQuantity())
                .build();
    }

    public Posting toPosting(PostingDto postingDto, Facility facility) {
        return Posting.builder()
                .id(postingDto.getId())
                .facility(facility)
                .productVariationId(postingDto.getProductVariationId())
                .quantity(postingDto.getQuantity())
                .build();
    }
}
