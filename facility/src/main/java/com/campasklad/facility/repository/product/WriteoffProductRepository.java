package com.campasklad.facility.repository.product;

import com.campasklad.facility.entity.product.WriteoffProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteoffProductRepository extends JpaRepository<WriteoffProduct, Long> {
}
