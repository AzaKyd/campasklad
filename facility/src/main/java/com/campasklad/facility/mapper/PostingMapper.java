package com.campasklad.facility.mapper;

import com.campasklad.facility.dto.PostingDto;
import com.campasklad.facility.entity.Facility;
import com.campasklad.facility.entity.Posting;
import org.springframework.stereotype.Component;

@Component
public class PostingMapper {

    public PostingDto toPostingDto(Posting posting) {
        return PostingDto.builder()
                .id(posting.getId())
                .facilityId(posting.getFacility() != null ? posting.getFacility().getId() : null)
                .status(posting.getStatus())
                .build();
    }

    public Posting toPosting(PostingDto postingDto, Facility facility) {
        return Posting.builder()
                .id(postingDto.getId())
                .facility(facility)
                .status(postingDto.getStatus())
                .build();
    }
}
