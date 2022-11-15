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

    @Test
    @DisplayName("업태구분명 중 하나라도 맞으며 도로명주소에 특정 문자열을 포함하는 레코드가 모두 찾아지는지")
    void findByBusinessTypeNameInAndRoadNameAddress() {
        List<String> includes = new ArrayList<>();
        includes.add("보건소");
        includes.add("보건지소");
        includes.add("보건진료소");
        String address = "광진구";
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameInAndRoadNameAddressContaining(includes, address);

        for (Hospital hospital : hospitals) {
            String businessTypeName = hospital.getBusinessTypeName();
            String roadNameAddress = hospital.getRoadNameAddress();
            assertTrue(businessTypeName.equals("보건소")
                    || businessTypeName.equals("보건지소") || businessTypeName.equals("보건진료소"));

            assertTrue(roadNameAddress.contains("광진구"));
            System.out.println(hospital.getHospitalName());
        }
    }

    @Test
    @DisplayName("특정 숫자 범위 안의 병상 수를 가지고 있는 모든 병원이 찾아지는지")
    void findByTotalNumberOfBeds() {
        List<Hospital> hospitals = hospitalRepository.findByTotalNumberOfBedsBetween(10, 19);
        for (Hospital hospital : hospitals) {
            int bedsNum = hospital.getTotalNumberOfBeds();
            System.out.println(bedsNum);
            assertTrue(bedsNum >= 10 && bedsNum < 20);
        }
    }

//    @Test
//    @DisplayName("특정 범위 내 병실 수를 가진 병원들이 병실 수의 내림차순으로 출력되는지")
//    void findByPatientRoomCount() {
//        List<Hospital> hospitals = hospitalRepository.findByPatientRoomCountBetweenOrderByDesc(10, 20);
//        int roomNum = 20;
//        for (Hospital hospital : hospitals) {
//            assertTrue(hospital.getPatientRoomCount() < roomNum);
//            roomNum = hospital.getPatientRoomCount();
//        }
//    }
}