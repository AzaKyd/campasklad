package com.campasklad.facility.excetion;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ExceptionType {
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST, "Entity not found"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Validation failed"),
    ACCESS_DENIED(HttpStatus.BAD_REQUEST, "Access denied");

    HttpStatus code;
    String message;
}
