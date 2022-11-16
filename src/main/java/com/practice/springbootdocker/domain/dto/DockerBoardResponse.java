package com.practice.springbootdocker.domain.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DockerBoardResponse {

    private Long id;
    private String title;
    private String contents;
    private String author;


}
