package com.fixit.server.domain.vehicle;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VehicleRequestDto {
    private Long customerId;
    private Long modelId;
    private String plateNumber;
    private Integer lastMileage;
}