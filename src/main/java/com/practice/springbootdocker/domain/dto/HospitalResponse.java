package com.practice.springbootdocker.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder // 객체 생성 시 하나하나 보면서 순서대로 매개 변수를 넣지 않아도 생성에 무리가 없게 해준다. 멤버 변수 이름 별로 지정이 가능해지기 때문.
@Getter
@AllArgsConstructor
public class HospitalResponse {

    private Integer id;
    private String roadNameAddress;
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;
    private String businessStatusName;

    public HospitalResponse(Integer id, String roadNameAddress, String hospitalName, Integer patientRoomCount,
                            Integer totalNumberOfBeds, String businessTypeName, Float totalAreaSize) {
        this.id = id;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
        this.patientRoomCount = patientRoomCount;
        this.totalNumberOfBeds = totalNumberOfBeds;
        this.businessTypeName = businessTypeName;
        this.totalAreaSize = totalAreaSize;
    }

    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }

    //    public HospitalResponse(Integer id, String hospitalName) {
//        this.id = id;
//        this.hospitalName = hospitalName;
//    }
}
