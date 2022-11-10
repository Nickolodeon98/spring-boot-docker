package com.practice.springbootdocker.domain.dto;

import com.practice.springbootdocker.domain.entity.DockerBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class DockerBoardDto {
    private Long id;
    private String title;
    private String contents;
    private String author;
    public DockerBoard toEntity() {
        return new DockerBoard(this.id, this.title, this.contents, this.author);
    }
}
