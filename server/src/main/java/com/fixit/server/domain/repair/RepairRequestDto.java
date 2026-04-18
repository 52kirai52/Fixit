package com.fixit.server.domain.repair;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RepairRequestDto {
    private Long vehicleId;
    private String plateNumber;
}