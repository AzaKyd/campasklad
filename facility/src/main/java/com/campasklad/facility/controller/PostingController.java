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
@RequestMapping("/api/facility/posting")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostingController {

    PostingService postingService;

    @GetMapping("/get/{id}")
    public ResponseEntity<PostingProductRequestDto> getPosting(@PathVariable Long id) {
        return ResponseEntity.ok().body(postingService.getPostingById(id));
    }

    @GetMapping("/delete")
    public ResponseEntity<PostingProductRequestDto> deletePosting(@RequestParam Long id) {
        postingService.deletePosting(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<PostingDto>> filterPosting(@RequestBody PostingFilterDto productFilterDto,
                                                           @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(postingService.getFilteredPosting(productFilterDto, pageable));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createPosting(@RequestBody PostingProductRequestDto postingProductRequestDto) {
        postingService.createPostingProduct(postingProductRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updatePosting(@RequestBody PostingProductRequestDto postingProductRequestDto) {
        postingService.updatePosting(postingProductRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/approve")
    public ResponseEntity<Void> approvePosting(@RequestParam Long id) {
        postingService.approvePosting(id);
        return ResponseEntity.ok().build();
    }
}
