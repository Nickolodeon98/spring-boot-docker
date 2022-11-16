package com.practice.springbootdocker.service;

import com.practice.springbootdocker.domain.dto.DockerBoardResponse;
import com.practice.springbootdocker.domain.entity.DockerBoard;
import com.practice.springbootdocker.repository.DockerBoardRepository;

import java.util.Optional;

public class DockerBoardService {
    private final DockerBoardRepository dockerBoardRepository;

    public DockerBoardService(DockerBoardRepository dockerBoardRepository) {
        this.dockerBoardRepository = dockerBoardRepository;
    }

    public DockerBoardResponse getDockerBoard(long l) {
        Optional<DockerBoard> optionalDockerBoard = dockerBoardRepository.findById(3L);
        if (optionalDockerBoard.isPresent())
            return DockerBoard.of(optionalDockerBoard.get());

        return null;
    }
}
