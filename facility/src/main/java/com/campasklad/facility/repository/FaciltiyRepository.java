package com.campasklad.facility.repository;

import com.campasklad.facility.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaciltiyRepository extends JpaRepository<Facility, Long> {
}
