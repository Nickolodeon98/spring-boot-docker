package com.practice.springbootdocker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.springbootdocker.domain.dto.DockerBoardAddRequest;
import com.practice.springbootdocker.domain.dto.DockerBoardAddResponse;
import com.practice.springbootdocker.domain.dto.DockerBoardResponse;
import com.practice.springbootdocker.domain.entity.DockerBoard;
import com.practice.springbootdocker.service.DockerBoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        DockerBoardResponse dockerBoardResponse = dockerBoardService.getDockerBoard(4L);
        given(dockerBoardResponse).willReturn(
                DockerBoardResponse.builder()
                        .id(4L)
                        .title("title")
                        .contents("contents")
                        .author("nick")
                        .build());

//        int id = 3;
        String url = "/api/v1/notice/rest/4";

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.contents").value("contents"))
                .andExpect(jsonPath("$.author").exists())
                .andDo(print());

        verify(dockerBoardService).getDockerBoard(4L); // 메서드 실행이 있었는지 확인한다.
    }

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("DB에 명시된 항목을 인서트한다.")
    @Test
    void add() throws Exception {
        DockerBoardAddRequest dockerBoardAddRequest = new DockerBoardAddRequest("제목입니다.", "내용입니다.", "전승환");
        given(dockerBoardService.addDockerBoard(dockerBoardAddRequest))
                .willReturn(new DockerBoardAddResponse(1L, "제목입니다.", "내용입니다.", "전승환"));

        mockMvc.perform(post("/api/v1/notice/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(dockerBoardAddRequest)))
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.contents").exists())
                .andExpect(jsonPath("$.author").exists())
                .andDo(print());

        verify(dockerBoardService).addDockerBoard(dockerBoardAddRequest);

    }
}