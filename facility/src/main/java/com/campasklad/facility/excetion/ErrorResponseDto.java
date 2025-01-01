package com.campasklad.facility.excetion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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