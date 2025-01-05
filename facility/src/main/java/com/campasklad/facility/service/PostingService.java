package com.campasklad.facility.service;

import com.campasklad.facility.dto.PostingDto;
import com.campasklad.facility.dto.filter.PostingFilterDto;
import com.campasklad.facility.dto.request.PostingProductRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostingService {

    void createPostingProduct(PostingProductRequestDto postingProductRequestDto);

    PostingProductRequestDto getPostingById(Long id);

    void updatePosting(PostingProductRequestDto postingProductRequestDto);

    void deletePosting(Long id);

    void approvePosting(Long id);

    Page<PostingDto> getFilteredPosting(PostingFilterDto postingFilterDto, Pageable pageable);
}
