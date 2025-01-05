package com.campasklad.facility.dto.filter;

import com.campasklad.facility.entity.Posting;
import com.campasklad.facility.entity.Writeoff;
import com.campasklad.facility.enums.DocumentStatus;
import jakarta.persistence.criteria.Predicate;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WriteoffFilterDto {

    DocumentStatus status;
    Long facilityId;
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    LocalDateTime createdFrom;
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    LocalDateTime createdTo;

    public static Specification<Writeoff> filterPostings(WriteoffFilterDto filterDto) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (filterDto.getStatus() != null) {
                predicate = criteriaBuilder.equal(criteriaBuilder.lower(root.get("status")), filterDto.getStatus());

            }

            if (filterDto.getFacilityId() != null) {
                predicate = criteriaBuilder.equal(root.get("facilityId"), filterDto.getFacilityId());
            }

            if (filterDto.getCreatedFrom() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), filterDto.getCreatedFrom()));
            }

            if (filterDto.getCreatedTo() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), filterDto.getCreatedTo()));
            }

            return predicate;
        };
    }
}
