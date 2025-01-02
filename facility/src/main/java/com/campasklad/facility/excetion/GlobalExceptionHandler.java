package com.campasklad.facility.excetion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponseDto> handleBaseServiceException(BaseException exception, WebRequest request) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
            request.getContextPath(),
            exception.getCode(),
            exception.getMessage(),
            LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientError(HttpClientErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode())
                .body("Ошибка при вызове внешнего сервиса: " + ex.getMessage());
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<String> handleRestClientError(RestClientException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ошибка взаимодействия с внешним сервисом: " + ex.getMessage());
    }
}
