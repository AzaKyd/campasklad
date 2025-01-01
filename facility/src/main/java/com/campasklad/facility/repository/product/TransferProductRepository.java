package com.campasklad.facility.repository.product;

import com.campasklad.facility.enitity.product.TransferProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferProductRepository extends JpaRepository<TransferProduct, Long> {
}
