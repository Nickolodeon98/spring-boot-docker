package com.practice.springbootdocker.domain.dto;

import com.practice.springbootdocker.domain.entity.Hospital;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class HospitalDto {

    private String openServiceName;
    private int openLocalGovernmentCode;
    private String managementNumber;
    private LocalDateTime licenseDate;
    private int businessStatus;
    private int businessStatusCode;
    private String phone;
    private String fullAddress;
    private String roadNameAddress;
    private String hospitalName;
    private String businessTypeName;
    private int healthcareProviderCount;
    private int patientRoomCount;
    private int totalNumberOfBeds;
    private float totalAreaSize;

    // DTO 의 엔티티화 - 필요한 이유: 앞단에서 받은 데이터를 엔티티로 바꿔야 DB 활용이 가능하기 때문이다.
    public Hospital toEntity() {
        return new Hospital(openServiceName, openLocalGovernmentCode, managementNumber,
                licenseDate, businessStatus, businessStatusCode, phone, fullAddress, roadNameAddress, hospitalName,
                businessTypeName, healthcareProviderCount, patientRoomCount, totalNumberOfBeds, totalAreaSize);
    }
}
