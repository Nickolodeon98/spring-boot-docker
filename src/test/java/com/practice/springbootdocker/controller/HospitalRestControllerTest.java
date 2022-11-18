package com.practice.springbootdocker.controller;

import com.practice.springbootdocker.domain.dto.HospitalResponse;
import com.practice.springbootdocker.service.HospitalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HospitalRestController.class)
public class HospitalRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    /* MockBean 을 사용함으로서 가짜 객체를 생성해주고 DB 와 상관 없이 테스트가 가능해진다.
     * 그러므로 @Autowired 하위에 있는 어노테이션이 아니다. */
    @MockBean
    HospitalService hospitalService;

    /* Service 가 아니라 Controller 만 검증하는 테스트이다. */
    @Test
    @DisplayName("Json 형태로 잘 반환하는지")
    void jsonResponse() throws Exception {
        /* {"id":2321,"roadNameAddress":"서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)",
        "hospitalName":"노소아청소년과의원","patientRoomCount":0,"totalNumberOfBeds":0,"businessTypeName":"의원",
        "totalAreaSize":0.0,"businessStatusName":"영업중"} */
        HospitalResponse hospitalResponse = HospitalResponse.builder()
                .id(2321)
                .roadNameAddress("서울특별시 서초구 서초중앙로 230, 202호 (반포동, 동화반포프라자빌딩)")
                .hospitalName("노소아청소년과의원")
                .patientRoomCount(0)
                .totalNumberOfBeds(0)
                .businessTypeName("의원")
                .totalAreaSize(0.0f)
                .businessStatusName("영업중")
                .build();

        given(hospitalService.hospitalResponse(2321)).willReturn(hospitalResponse); // WebMvcTest 하위 메서드인지?

        Integer hospitalId = 2321;

        String url = String.format("/api/v1/hospital/response/%d", hospitalId);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hospitalName").exists()) // 달러 사인은 루트를 뜻한다. 여기서는 hospitalResponse.
                .andExpect(jsonPath("$.hospitalName").value("노소아청소년과의원"))
                .andDo(print()); // http request 와 response 내용을 출력한다.

        verify(hospitalService).hospitalResponse(hospitalId);

    }



}