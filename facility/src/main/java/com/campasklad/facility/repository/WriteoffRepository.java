package com.campasklad.facility.repository;

import com.campasklad.facility.entity.Writeoff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteoffRepository extends JpaRepository<Writeoff, Long> {
}
