package com.practice.springbootdocker.repository;

import com.practice.springbootdocker.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
