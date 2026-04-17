package com.fixit.server.domain.vehiclemodel;

import lombok.Getter;

@Getter
public class VehicleModelResponseDto {
    private Long id;
    private String name;

    public VehicleModelResponseDto(VehicleModel vehicleModel) {
        this.id = vehicleModel.getId();
        this.name = vehicleModel.getName();
    }
}