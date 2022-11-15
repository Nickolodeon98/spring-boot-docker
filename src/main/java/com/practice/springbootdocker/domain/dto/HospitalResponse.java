package com.practice.springbootdocker.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HospitalResponse {

    private Integer id;
    private String roadNameAddress;
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;

//    public HospitalResponse(Integer id, String hospitalName) {
//        this.id = id;
//        this.hospitalName = hospitalName;
//    }
}
