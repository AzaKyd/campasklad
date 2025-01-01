package com.campasklad.facility.repository;

import com.campasklad.facility.enitity.FacilityProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityProductRepository extends JpaRepository<FacilityProduct, Long> {
}
