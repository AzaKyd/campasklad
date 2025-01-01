package com.campasklad.facility.repository.product;

import com.campasklad.facility.enitity.product.FacilityProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityProductRepository extends JpaRepository<FacilityProduct, Long> {
}
