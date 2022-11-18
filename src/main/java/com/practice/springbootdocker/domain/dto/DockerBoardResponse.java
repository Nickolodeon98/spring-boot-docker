package com.practice.springbootdocker.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class DockerBoardResponse {

    private Long id;
    private String title;
    private String contents;
    private String author;

}
