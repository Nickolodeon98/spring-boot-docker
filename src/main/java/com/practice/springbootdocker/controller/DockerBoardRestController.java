package com.practice.springbootdocker.controller;

import com.practice.springbootdocker.domain.dto.DockerBoardResponse;
import com.practice.springbootdocker.service.DockerBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DockerBoardRestController {
    private final DockerBoardService dockerBoardService;

    public DockerBoardRestController(DockerBoardService dockerBoardService) {
        this.dockerBoardService = dockerBoardService;
    }

    @RequestMapping(value="/api/vi/notice/rest/{id}", method = RequestMethod.GET)
    public ResponseEntity<DockerBoardResponse> getDockerBoardJson(@PathVariable Long id) {
        DockerBoardResponse dockerBoardResponse = null;
        try {
            dockerBoardResponse = dockerBoardService.getDockerBoard(id);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(dockerBoardResponse); // Json 형태의 응답을 리턴한다.
    }
}
