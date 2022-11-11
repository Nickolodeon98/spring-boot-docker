package com.practice.springbootdocker.controller;

import com.practice.springbootdocker.domain.dto.ReviewDto;
import com.practice.springbootdocker.domain.entity.Hospital;
import com.practice.springbootdocker.domain.entity.Review;
import com.practice.springbootdocker.repository.ReviewRepository;
import com.practice.springbootdocker.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notice/hospitals")
@Slf4j
public class HospitalController {
    private HospitalService hospitalService;

    private ReviewRepository reviewRepository;

    public HospitalController(HospitalService hospitalService, ReviewRepository reviewRepository) {
        this.hospitalService = hospitalService;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/info")
    public String pagingHospitalsInfo(Model model, @PageableDefault(size = 20, sort="id", direction= Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute("information",  hospitalService.hospitalPage(pageable));
        model.addAttribute("previous", pageable.previousOrFirst());
        model.addAttribute("next", pageable.next());
        return "hospital/hospitals";
    }

    @GetMapping("/result")
    public String searchKeyword(String keyword, Model model, @PageableDefault(size = 20, sort="id", direction= Sort.Direction.ASC) Pageable pageable) {
        model.addAttribute("search",hospitalService.searchHospitalName(keyword, pageable));
        model.addAttribute("keyword", keyword);
        model.addAttribute("next", pageable.next());
        model.addAttribute("previous", pageable.previousOrFirst());
        return "dockerboard/search";
    }

    @GetMapping("/{id}")
    public String selectSingleHospital(@PathVariable Integer id, Model model) {
        model.addAttribute("single", hospitalService.selectHospital(id));
        List<Review> reviews = reviewRepository.findByHospitalToReview_Id(id);
        model.addAttribute("reviews", reviews);
        return "hospital/show";
    }

    @PostMapping("{id}/review")
    public String addReview(@PathVariable Integer id, ReviewDto reviewDto) {
        Hospital hospital = hospitalService.selectHospital(id);
        Review savedReview = reviewRepository.save(reviewDto.toEntity(hospital));
        log.info("id:{} author:{} contents:{}", savedReview.getId(), savedReview.getAuthor(), savedReview.getContents());
        return String.format("redirect:/notice/hospitals/%d", id);
    }

    @PostMapping("{id}/review/none")
    public String deleteReview(@PathVariable Integer id) {
        Hospital hospital = hospitalService.findHospitalFromReview(id);
        reviewRepository.deleteById(id);
        return String.format("redirect:/notice/hospitals/%d", hospital.getId());
    }

}
