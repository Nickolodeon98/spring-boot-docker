package com.practice.springbootdocker.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String contents;

    @ManyToOne
    private DockerBoard postToComment;

    public Comment(String author, String contents, DockerBoard dockerBoard) {
        this.author = author;
        this.contents = contents;
        this.postToComment = dockerBoard;
    }
}
