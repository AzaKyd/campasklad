package com.campasklad.facility.repository;

import com.campasklad.facility.enitity.WriteOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteOffRepository extends JpaRepository<WriteOff, Long> {
}
