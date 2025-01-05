package com.campasklad.facility.service;

import com.campasklad.facility.dto.PostingDto;
import com.campasklad.facility.dto.request.PostingProductRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostingService {

    void createPostingProduct(PostingProductRequestDto postingProductRequestDto);

    PostingProductRequestDto getPostingProducts(Long id);

    void updatePosting(PostingProductRequestDto postingProductRequestDto);

    void deletePosting(Long id);

    void approvePosting(Long id);

    Page<PostingDto> getPostingProducts(Pageable pageable);
}
