package com.practice.springbootdocker.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class DockerBoardAddResponse {
    Long id;
    String title;
    String contents;
    String author;
}
