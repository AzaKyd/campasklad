package com.campasklad.facility.repository.product;

import com.campasklad.facility.entity.product.PostingProduct;
import com.campasklad.facility.entity.product.WriteoffProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WriteoffProductRepository extends JpaRepository<WriteoffProduct, Long> {
    List<WriteoffProduct> findAllByWriteoffById(Long writeoffId);

    void deleteAllByWriteoffById(Long postingId);
}
