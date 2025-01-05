package com.campasklad.facility.dto.filter;

import com.campasklad.facility.entity.Posting;
import com.campasklad.facility.enums.DocumentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class PostingFilterDto {

    DocumentStatus status;
    Long facilityId;
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    LocalDateTime createdFrom;
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    LocalDateTime createdTo;

    public static Specification<Posting> filterPostings(PostingFilterDto filterDto) {
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
