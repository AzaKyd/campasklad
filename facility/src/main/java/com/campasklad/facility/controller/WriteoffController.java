package com.campasklad.facility.controller;

import com.campasklad.facility.dto.PostingDto;
import com.campasklad.facility.dto.WriteoffDto;
import com.campasklad.facility.dto.filter.PostingFilterDto;
import com.campasklad.facility.dto.filter.WriteoffFilterDto;
import com.campasklad.facility.dto.request.PostingProductRequestDto;
import com.campasklad.facility.dto.request.WriteoffProductRequestDto;
import com.campasklad.facility.entity.Writeoff;
import com.campasklad.facility.service.PostingService;
import com.campasklad.facility.service.WriteoffService;
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
public class WriteoffController {
    WriteoffService writeoffService;

    @GetMapping("/get-posting/{id}")
    public ResponseEntity<WriteoffProductRequestDto> getPosting(@PathVariable Long id) {
        return ResponseEntity.ok().body(writeoffService.getWriteoffById(id));
    }

    @GetMapping("/delete")
    public ResponseEntity<WriteoffProductRequestDto> deletePosting(@RequestParam Long id) {
        writeoffService.deleteWriteoff(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter-posting")
    public ResponseEntity<Page<WriteoffDto>> filterPosting(@RequestBody WriteoffFilterDto writeoffFilterDto,
                                                           @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(writeoffService.getFilteredWriteoff(writeoffFilterDto, pageable));
    }

    @PostMapping("/create-posting")
    public ResponseEntity<Void> createPosting(@RequestBody WriteoffProductRequestDto writeoffProductRequestDto) {
        writeoffService.createWriteoff(writeoffProductRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-posting")
    public ResponseEntity<Void> updatePosting(@RequestBody WriteoffProductRequestDto writeoffProductRequestDto) {
        writeoffService.updateWriteoff(writeoffProductRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/approve")
    public ResponseEntity<Void> approvePosting(@RequestParam Long id) {
        writeoffService.approveWriteoff(id);
        return ResponseEntity.ok().build();
    }
}
