package com.team19.carmicroservice.repository;

import com.team19.carmicroservice.model.TransmissionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionTypeRepository extends JpaRepository<TransmissionType, Long> {
}
