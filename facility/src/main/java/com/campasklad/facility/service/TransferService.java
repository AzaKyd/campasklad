package com.campasklad.facility.service;

import com.campasklad.facility.dto.WriteoffDto;
import com.campasklad.facility.dto.filter.WriteoffFilterDto;
import com.campasklad.facility.dto.request.WriteoffProductRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransferService {

    void createTransfer(WriteoffProductRequestDto writeoffProductRequestDto);

    WriteoffProductRequestDto getTransferById(Long id);

    void updateTransfer(WriteoffProductRequestDto writeoffProductRequestDto);

    void deleteTransfer(Long id);

    void approveTransfer(Long id);

    Page<WriteoffDto> getFilteredTransfer(WriteoffFilterDto writeoffFilterDto, Pageable pageable);
}
