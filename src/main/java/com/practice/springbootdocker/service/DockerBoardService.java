package com.practice.springbootdocker.service;

import com.practice.springbootdocker.domain.dto.DockerBoardAddRequest;
import com.practice.springbootdocker.domain.dto.DockerBoardAddResponse;
import com.practice.springbootdocker.domain.dto.DockerBoardResponse;
import com.practice.springbootdocker.domain.entity.DockerBoard;
import com.practice.springbootdocker.repository.DockerBoardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DockerBoardService {
    private final DockerBoardRepository dockerBoardRepository;

    public DockerBoardService(DockerBoardRepository dockerBoardRepository) {
        this.dockerBoardRepository = dockerBoardRepository;
    }

    public DockerBoardResponse getDockerBoard(long id) {
        Optional<DockerBoard> optionalDockerBoard = dockerBoardRepository.findById(id);
        if (optionalDockerBoard.isPresent())
            return DockerBoard.of(optionalDockerBoard.get());

        return null;
    }
    /* Response 와 Request 로 나누는 이유는 받아드린 데이터를 그대로 리턴하기 원하지 않는 경우가 있기 때문이다.
     * 예) 비밀번호를 DTO 로 받은 후에 보이는 정보에선 비밀번호를 빼고 싶은 경우 */
    public DockerBoardAddResponse addDockerBoard(DockerBoardAddRequest dockerBoardAddRequest) {
        DockerBoard savedDockerBoard = dockerBoardRepository.save(dockerBoardAddRequest.toEntity());
        return DockerBoard.addResponseOf(savedDockerBoard);
    }


}
