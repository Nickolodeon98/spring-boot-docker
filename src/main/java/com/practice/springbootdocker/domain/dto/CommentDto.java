package com.practice.springbootdocker.domain.dto;


import com.practice.springbootdocker.domain.entity.Comment;
import com.practice.springbootdocker.domain.entity.DockerBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentDto {
    private String author;
    private String contents;

    public Comment toEntity(DockerBoard dockerBoard) {
        return new Comment(this.author, this.contents, dockerBoard);
    }
}
