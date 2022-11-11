package com.practice.springbootdocker.repository;

import com.practice.springbootdocker.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByHospitalToReview_Id(Integer id);
}
