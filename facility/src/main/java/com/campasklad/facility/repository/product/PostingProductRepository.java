package com.campasklad.facility.repository.product;

import com.campasklad.facility.entity.product.PostingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostingProductRepository extends JpaRepository<PostingProduct, Long> {

    List<PostingProduct> findAllByPostingId(Long postingId);
}
