package com.campasklad.facility.repository.product;

import com.campasklad.facility.dto.internal.ProductIdsDto;
import com.campasklad.facility.entity.product.FacilityProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityProductRepository extends JpaRepository<FacilityProduct, Long> {
    @Query(value = """
    SELECT fp.product_id AS productId, SUM(fp.quantity) AS totalQuantity
    FROM facility_products fp
    WHERE (:facilityId IS NULL OR fp.facility_id = :facilityId)
    GROUP BY fp.product_id
    ORDER BY fp.product_id DESC
    LIMIT :#{#pageable.pageSize} OFFSET :#{#pageable.offset}
    """,
            nativeQuery = true)
    List<ProductIdsDto> findGroupedProducts(
            Long facilityId,
            Pageable pageable
    );
}
