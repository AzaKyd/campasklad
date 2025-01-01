package com.campasklad.facility.dto;

import com.campasklad.facility.enums.DocumentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriteoffDto {

    Long id;
    Long facilityId;
    DocumentStatus status;
}
