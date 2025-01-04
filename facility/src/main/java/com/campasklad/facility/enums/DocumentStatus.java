package com.campasklad.facility.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum DocumentStatus {
    NEW,
    APPROVED
}
