package com.fixit.server.domain.repair;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RepairRequestDto {
    // 필수
    private String customerName;
    private String plateNumber;

    // 선택
    private String customerPhone;
    private String memo;

    // 나중에 추가 예정
    private Long vehicleId;
    private Long modelId;
    private String carModel;
    private Integer lastMileage;
}