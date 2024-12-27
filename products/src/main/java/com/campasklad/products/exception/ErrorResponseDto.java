package com.campasklad.products.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseDto {
    private String webRequest;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;
}