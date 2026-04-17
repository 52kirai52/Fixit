package com.fixit.server.domain.vehicle;

import lombok.Getter;

@Getter
public class VehicleResponseDto {
    private Long id;
    private String plateNumber;
    private String modelName;
    private String customerName;
    private Integer lastMileage;

    public VehicleResponseDto(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.plateNumber = vehicle.getPlateNumber();
        this.modelName = vehicle.getModel() != null ? vehicle.getModel().getName() : null;
        this.customerName = vehicle.getCustomer().getName();
        this.lastMileage = vehicle.getLastMileage();
    }
}