package com.practice.springbootdocker.repository;

import com.practice.springbootdocker.domain.entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
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

    @Test
    @DisplayName("업태구분명들 중 하나라도 맞는 레코드가 모두 찾아지는지")
    void findByBusinessTypeNameIn() {
        System.out.println(System.getProperties());
        List<String> includes = new ArrayList<>();
        includes.add("보건소");
        includes.add("보건지소");
        includes.add("보건진료소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(includes);

        for (Hospital hospital : hospitals) {
            String businessTypeName = hospital.getBusinessTypeName();
            assertTrue(businessTypeName.equals("보건소")
                    || businessTypeName.equals("보건지소") || businessTypeName.equals("보건진료소"));
        }
    }
}