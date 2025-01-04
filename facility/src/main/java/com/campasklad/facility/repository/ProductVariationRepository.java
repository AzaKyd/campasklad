package com.campasklad.facility.repository;

import com.campasklad.facility.entity.Color;
import com.campasklad.facility.entity.ProductVariation;
import com.campasklad.facility.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductVariationRepository extends JpaRepository<ProductVariation, Long> {
    Optional<ProductVariation> findProductVariationByProductIdAndSizeAndColor(Long id, Size size, Color color);
    Boolean existsProductVariationByProductIdAndSizeAndColor(Long id, Size size, Color color);
}
