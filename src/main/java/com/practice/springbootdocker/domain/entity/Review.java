package com.practice.springbootdocker.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String author;
    private String contents;

    @ManyToOne
    private Hospital hospitalToReview;

    public Review(String author, String contents, Hospital hospitalToReview) {
        this.author = author;
        this.contents = contents;
        this.hospitalToReview = hospitalToReview;
    }
}
