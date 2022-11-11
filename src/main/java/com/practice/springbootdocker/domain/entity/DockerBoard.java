package com.practice.springbootdocker.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Table(name="docker_board")
public class DockerBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String contents;
    private String author;

    public DockerBoard(Long id, String title, String contents, String author) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.author = author;
    }

    @OneToMany(mappedBy = "postToComment")
    private List<Comment> comments = new ArrayList<>();
}
