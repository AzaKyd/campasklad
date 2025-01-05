package com.campasklad.facility.repository;

import com.campasklad.facility.entity.Posting;
import com.campasklad.facility.entity.Writeoff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteoffRepository extends JpaRepository<Writeoff, Long>, JpaSpecificationExecutor<Writeoff> {
    default Page<Writeoff> findWithFilter(Specification<Writeoff> specification, Pageable pageable) {
        if(pageable.getSort().isUnsorted()){
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.desc("id")));
        }
        return findAll(specification, pageable);
    }
}
