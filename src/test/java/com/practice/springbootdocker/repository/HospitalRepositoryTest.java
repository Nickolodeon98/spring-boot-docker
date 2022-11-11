package com.practice.springbootdocker.repository;

import com.practice.springbootdocker.domain.entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {
    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    @DisplayName("Hospital row 를 잘 찾는지")
    void hospitalGetTest() {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(1);
        Hospital hospital = null;
        if (optionalHospital.isPresent()) {
            hospital = optionalHospital.get();
            assertEquals(1, hospital.getId());
        } else fail();
    }
}