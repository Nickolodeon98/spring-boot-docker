package com.practice.springbootdocker.controller;

import com.practice.springbootdocker.domain.dto.DockerBoardDto;
import com.practice.springbootdocker.domain.entity.DockerBoard;
import com.practice.springbootdocker.repository.DockerBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/notice")
@Slf4j
public class DockerBoardController {

    private final DockerBoardRepository dockerBoardRepository; // DI 해준다.

    public DockerBoardController(DockerBoardRepository dockerBoardRepository) {
        this.dockerBoardRepository = dockerBoardRepository;
    }

    @GetMapping("/new")
    public String createNewPostForm() {
        return "dockerboard/new";
    }

    @PostMapping("/post")
    public String createNewPost(DockerBoardDto dockerBoardDto) {
        log.info("Title:{} Contents:{} Author:{}", dockerBoardDto.getTitle(), dockerBoardDto.getContents(), dockerBoardDto.getAuthor());
        DockerBoard savedDockerBoard = dockerBoardRepository.save(dockerBoardDto.toEntity());
        return "";
    }

    @GetMapping("/{id}")
    public String selectSingleRecord(@PathVariable Long id, Model model) {
        Optional<DockerBoard> optDockerBoard = dockerBoardRepository.findById(id);
        if (optDockerBoard.isPresent()) {
            model.addAttribute("record", optDockerBoard);
            return "dockerboard/show";
        } else {
            model.addAttribute("message", String.format("id %d를 찾을 수 없습니다", id));
            return "error";
        }
    }
}
