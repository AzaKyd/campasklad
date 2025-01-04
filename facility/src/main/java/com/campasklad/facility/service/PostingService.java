package com.campasklad.facility.service;

import com.campasklad.facility.dto.request.PostingProductRequestDto;

import java.util.List;

public interface PostingService {

    void createPostingProduct(PostingProductRequestDto postingProductRequestDto);

    PostingProductRequestDto getPostingProducts(Long id);

    void updatePosting(PostingProductRequestDto postingProductRequestDto);
}
