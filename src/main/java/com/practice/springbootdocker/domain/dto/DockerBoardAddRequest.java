package com.practice.springbootdocker.domain.dto;

import com.practice.springbootdocker.domain.entity.DockerBoard;
import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class DockerBoardAddRequest {
    String title;
    String contents;
    String author;
    public DockerBoard toEntity() {
        return DockerBoard.builder()
                .title(this.title)
                .contents(this.contents)
                .author(this.author)
                .build();
    }
}
