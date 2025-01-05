package com.campasklad.facility.controller;

import com.campasklad.facility.dto.PostingDto;
import com.campasklad.facility.dto.filter.PostingFilterDto;
import com.campasklad.facility.dto.request.PostingProductRequestDto;
import com.campasklad.facility.service.PostingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facility")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostingController {

    PostingService postingService;

    @GetMapping("/get-posting/{id}")
    public ResponseEntity<PostingProductRequestDto> getPosting(@PathVariable Long id) {
        postingService.getPostingProducts(id);
        return ResponseEntity.ok().body(postingService.getPostingProducts(id));
    }

    @GetMapping("/delete")
    public ResponseEntity<PostingProductRequestDto> deletePosting(@RequestParam Long id) {
        postingService.getPostingProducts(id);
        return ResponseEntity.ok().body(postingService.getPostingProducts(id));
    }

    @GetMapping("/filter-posting")
    public ResponseEntity<Page<PostingDto>> filterProducts(@RequestBody PostingFilterDto productFilterDto,
                                                           @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(postingService.getPostingProducts(productFilterDto, pageable));
    }


    @PostMapping("/create-posting")
    public ResponseEntity<Void> createPosting(@RequestBody PostingProductRequestDto postingProductRequestDto) {
        postingService.createPostingProduct(postingProductRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-posting")
    public ResponseEntity<Void> updatePosting(@RequestBody PostingProductRequestDto postingProductRequestDto) {
        postingService.createPostingProduct(postingProductRequestDto);
        return ResponseEntity.ok().build();
    }
}
