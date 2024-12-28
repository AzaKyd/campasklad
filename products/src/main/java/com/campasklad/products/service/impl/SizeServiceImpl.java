package com.campasklad.products.service.impl;

import com.campasklad.products.dto.SizeDto;
import com.campasklad.products.entity.Size;
import com.campasklad.products.exception.BaseException;
import com.campasklad.products.exception.ExceptionType;
import com.campasklad.products.mapper.SizeMapper;
import com.campasklad.products.repository.SizeRepository;
import com.campasklad.products.service.SizeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SizeServiceImpl implements SizeService {

    SizeRepository sizeRepository;
    SizeMapper sizeMapper;

    @Override
    public void createSize(SizeDto sizeDto) {
        sizeRepository.save(sizeMapper.toEntity(sizeDto));
    }

    @Override
    public SizeDto getSizeById(Long id) {
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        return sizeMapper.toDto(size);
    }

    @Override
    public List<SizeDto> getAllSeasons() {
        return sizeRepository.findAll().stream().map(sizeMapper::toDto).toList();
    }

    @Override
    public void updateSize(SizeDto sizeDto) {
        Size size = sizeRepository.findById(sizeDto.getId())
                .orElseThrow(() -> new BaseException(ExceptionType.ENTITY_NOT_FOUND));
        size.setName(sizeDto.getName());
        sizeRepository.save(size);
    }

    @Override
    public void deleteSize(Long id) {
        if(!sizeRepository.existsById(id)) {
            throw new BaseException(ExceptionType.ENTITY_NOT_FOUND);
        }
        sizeRepository.deleteById(id);
    }
}
