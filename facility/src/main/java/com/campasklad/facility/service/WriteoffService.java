package com.campasklad.facility.service;

import com.campasklad.facility.dto.PostingDto;
import com.campasklad.facility.dto.WriteoffDto;
import com.campasklad.facility.dto.filter.WriteoffFilterDto;
import com.campasklad.facility.dto.request.WriteoffProductRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WriteoffService {

    void createWriteoff(WriteoffProductRequestDto writeoffProductRequestDto);

    WriteoffProductRequestDto getWriteoffById(Long id);

    void updateWriteoff(WriteoffProductRequestDto writeoffProductRequestDto);

    void deleteWriteoff(Long id);

    void approveWriteoff(Long id);

    Page<WriteoffDto> getFilteredWriteoff(WriteoffFilterDto writeoffFilterDto, Pageable pageable);
}