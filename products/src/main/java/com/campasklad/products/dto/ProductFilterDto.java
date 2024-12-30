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
            Predicate predicate = criteriaBuilder.conjunction();

            if (filterDto.getName() != null && !filterDto.getName().isEmpty()) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%" + filterDto.getName().toLowerCase() + "%")
                );
            }

            if (filterDto.getCategoryId() != null) {
                predicate = criteriaBuilder.equal(root.get("categoryId"), filterDto.getCategoryId());
            }

            if (filterDto.getSeasonId() != null) {
                predicate = criteriaBuilder.equal(root.get("seasonId"), filterDto.getCategoryId());
            }

            if (filterDto.getMinPrice() != null) {
                predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), filterDto.getMinPrice());
            }

            if (filterDto.getMaxPrice() != null) {
                predicate = criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), filterDto.getMaxPrice());
            }

            return predicate;
        };
    }
}
