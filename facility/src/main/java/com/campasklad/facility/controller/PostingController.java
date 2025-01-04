package com.campasklad.facility.controller;

import com.campasklad.facility.dto.request.PostingProductRequestDto;
import com.campasklad.facility.service.PostingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facility")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostingController {

    PostingService postingService;

    @PostMapping("create-posting")
    public ResponseEntity<Void> createPosting(@RequestBody PostingProductRequestDto postingProductRequestDto) {
        postingService.createPostingProduct(postingProductRequestDto);
        return ResponseEntity.ok().build();
    }
}
