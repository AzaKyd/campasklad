package com.campasklad.facility.dto;

import com.campasklad.facility.enitity.Facility;
import com.campasklad.facility.enums.DocumentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.tools.DocumentationTool;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferDto {

    Long id;
    Long sourceFacilityId;
    Long destinationFacilityId;
    DocumentStatus status;
}
