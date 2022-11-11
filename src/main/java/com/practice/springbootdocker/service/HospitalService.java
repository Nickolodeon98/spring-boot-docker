package com.practice.springbootdocker.service;

import com.practice.springbootdocker.domain.entity.Hospital;
import com.practice.springbootdocker.repository.HospitalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public Page<Hospital> hospitalPage(Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);
        return hospitals;
    }
    @Transactional
    public Page<Hospital> searchHospitalName(String keyword, Pageable pageable) {
        return hospitalRepository.findByHospitalNameContaining(keyword, pageable);
    }

    @Transactional
    public Hospital selectHospital(Integer id) {
        Optional<Hospital> optHospital = hospitalRepository.findById(id);
        Hospital hospital = null;
        if (optHospital.isPresent()) {
            hospital = optHospital.get();
            return hospital;
        }
        return null;
    }
}
