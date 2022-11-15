package com.practice.springbootdocker.controller;

import com.practice.springbootdocker.domain.dto.HospitalResponse;
import com.practice.springbootdocker.domain.entity.Hospital;
import com.practice.springbootdocker.repository.HospitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController // json 형식으로 데이터를 받는 컨트롤러이다.
@RequestMapping(value="api/v1/hospital")
public class HospitalRestController {
    HospitalRepository hospitalRepository;

    public HospitalRestController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    /* 엔티티와 DTO 를 분리한다. 엔티티는 JPA 와 관련 있지만 DTO 와는 거의 무관하기 때문이다.
     * 결국 엔티티를 화면으로 보내지 않고 완전히 DB 에만 관여하도록 분리하는 과정인 것이다. */
    @GetMapping("/response/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) {
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        HospitalResponse hospitalResponse = Hospital.of(optionalHospital.get());
        return ResponseEntity.ok().body(hospitalResponse);
    }

}
