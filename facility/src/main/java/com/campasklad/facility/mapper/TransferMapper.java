package com.campasklad.facility.mapper;


import com.campasklad.facility.dto.TransferDto;
import com.campasklad.facility.enitity.Facility;
import com.campasklad.facility.enitity.Transfer;
import org.springframework.stereotype.Component;

@Component
public class TransferMapper {

    public TransferDto toDto(Transfer transfer) {
        return TransferDto.builder()
                .id(transfer.getId())
                .sourceFacilityId(transfer.getSourceFacility() != null ? transfer.getSourceFacility().getId() : null)
                .destinationFacilityId(transfer.getDestinationFacility() != null ? transfer.getDestinationFacility().getId() : null)
                .status(transfer.getStatus())
                .build();
    }

    public Transfer toEntity(TransferDto transferDto, Facility sourceFacility, Facility destinationFacility) {
        return Transfer.builder()
                .id(transferDto.getId())
                .sourceFacility(sourceFacility)
                .destinationFacility(destinationFacility)
                .status(transferDto.getStatus())
                .build();
    }
}
