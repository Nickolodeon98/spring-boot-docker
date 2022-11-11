package com.practice.springbootdocker.domain.dto;

import com.practice.springbootdocker.domain.entity.Hospital;
import com.practice.springbootdocker.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class ReviewDto {

    private Integer id;

    private String author;
    private String contents;

    public Review toEntity(Hospital hospital) {
        return new Review(this.author, this.contents, hospital);
    }

}
