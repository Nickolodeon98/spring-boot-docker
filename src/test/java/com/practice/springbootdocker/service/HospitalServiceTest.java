package com.practice.springbootdocker.service;

import com.practice.springbootdocker.domain.entity.Hospital;
import com.practice.springbootdocker.repository.HospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HospitalServiceTest {

    private HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);

    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("코드를 보고 영업중과 폐업 상태를 구분한다.")
    void distinguishCode() {

        Optional<Hospital> hospital = Optional.of(Hospital.builder()
                        .hospitalName("의원")
                        .id(1)
                        .businessStatusCode(13)
                        .businessTypeName("피부과")
                        .build());

        Mockito.when(hospitalRepository.findById(1)).thenReturn(hospital);

        assertEquals("영업중", hospitalService.hospitalResponse(1).getBusinessStatusName());
    }



}