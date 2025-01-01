package com.campasklad.facility.repository.product;

import com.campasklad.facility.enitity.product.PostingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostingProductRepository extends JpaRepository<PostingProduct, Long> {
}
