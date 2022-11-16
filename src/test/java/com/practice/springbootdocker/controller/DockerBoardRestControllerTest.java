package com.practice.springbootdocker.controller;

import com.practice.springbootdocker.domain.dto.DockerBoardResponse;
import com.practice.springbootdocker.domain.entity.DockerBoard;
import com.practice.springbootdocker.service.DockerBoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* GET /api/v1/articles/{id} 의 결과값이 {"id":1 "title":"~~" "content":"~~"} 이 나오는지 확인하는 단위 테스트*/
@WebMvcTest(DockerBoardRestController.class)
class DockerBoardRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DockerBoardService dockerBoardService;

    @DisplayName("게시판에 저장된 Json 형태의 데이터를 리턴하는지")
    @Test
    void getJsonResponse() throws Exception {
        Optional<DockerBoard> optionalDockerBoard = dockerBoardService.getDockerBoard(3L);
        DockerBoardResponse dockerBoardResponse = DockerBoard.of(optionalDockerBoard.get());

        given(dockerBoardResponse).willReturn(
                new DockerBoardResponse(3L, "나의 일기", "오늘은 화창했다.", "전승환"));

        String id = "3L";
        String url = "/api/v1/notice/%d" + id;

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.contents").value("오늘은 화창했다."))
                .andExpect(jsonPath("$.author").exists())
                .andDo(print());

    }

}