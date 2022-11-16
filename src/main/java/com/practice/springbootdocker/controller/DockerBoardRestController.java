package com.practice.springbootdocker.controller;

import com.practice.springbootdocker.domain.dto.DockerBoardAddRequest;
import com.practice.springbootdocker.domain.dto.DockerBoardAddResponse;
import com.practice.springbootdocker.domain.dto.DockerBoardResponse;
import com.practice.springbootdocker.service.DockerBoardService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notice")
@Slf4j
public class DockerBoardRestController {
    private final DockerBoardService dockerBoardService;

    public DockerBoardRestController(DockerBoardService dockerBoardService) {
        this.dockerBoardService = dockerBoardService;
    }

    @GetMapping("/rest/{id}")
    public ResponseEntity<DockerBoardResponse> getDockerBoardJson(@PathVariable Long id) {
        DockerBoardResponse dockerBoardResponse = null;
        try {
            dockerBoardResponse = dockerBoardService.getDockerBoard(id);
            log.info(dockerBoardResponse.getTitle());
        } catch (NullPointerException e) {
        }
        return ResponseEntity.ok().body(dockerBoardResponse); // Json 형태의 응답을 리턴한다.
    }

    @PostMapping("/new")
    public ResponseEntity<DockerBoardAddResponse> addDockerBoardJson(@RequestBody DockerBoardAddRequest dto) {
        DockerBoardAddResponse dockerBoardAddResponse = dockerBoardService.addDockerBoard(dto);
        return ResponseEntity.ok().body(dockerBoardAddResponse);
    }
}
