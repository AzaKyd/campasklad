package com.campasklad.facility.dto;

import com.campasklad.facility.enums.DocumentStatus;
import com.campasklad.facility.enums.WriteoffReason;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Json
public class WriteoffDto {

    Long id;
    Long facilityId;
    DocumentStatus status;
    WriteoffReason writeoffReason;
    String massage;
}
