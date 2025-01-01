package com.campasklad.facility.dto;

import com.campasklad.facility.enitity.Facility;
import com.campasklad.facility.enums.DocumentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostingDto {

    Long id;
    DocumentStatus status;
    Long facilityId;
}
