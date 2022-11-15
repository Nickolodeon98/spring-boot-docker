package com.practice.springbootdocker.repository;

import com.practice.springbootdocker.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    Page<Hospital> findByHospitalNameContaining(String searchKey, Pageable pageable);

    Hospital findByReview_Id(Integer id);

    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);

    List<Hospital> findByBusinessTypeNameInAndRoadNameAddressContaining(List<String> businessTypes, String address);

    List<Hospital> findByTotalNumberOfBedsBetween(int gt, int lt);

//    List<Hospital> findByPatientRoomCountBetweenOrderByDesc(int gt, int lt);
}
