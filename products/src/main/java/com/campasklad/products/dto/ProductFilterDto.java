package com.campasklad.products.dto;

import com.campasklad.products.entity.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFilterDto {
    String name;
    BigDecimal minPrice;
    BigDecimal maxPrice;
    Long categoryId;
    Long seasonId;

    public static Specification<Product> filterProducts(ProductFilterDto filterDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filterDto.getName() != null && !filterDto.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + filterDto.getName().toLowerCase() + "%"
                ));
            }

            if (filterDto.getCategoryId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("categoryId"), filterDto.getCategoryId()));
            }

            if (filterDto.getSeasonId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("seasonId"), filterDto.getCategoryId()));
            }

            if (filterDto.getMinPrice() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), filterDto.getMinPrice()));
            }

            if (filterDto.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), filterDto.getMaxPrice()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
