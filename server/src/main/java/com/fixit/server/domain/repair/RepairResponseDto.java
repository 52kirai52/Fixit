package com.fixit.server.domain.repair;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class RepairResponseDto {
    private Long id;
    private String status;
    private String plateNumber;
    private String customerName;
    private String modelName;
    private LocalDateTime createdAt;

    public RepairResponseDto(Repair repair) {
        this.id = repair.getId();
        this.status = repair.getStatus();
        this.plateNumber = repair.getVehicle().getPlateNumber();
        this.customerName = repair.getVehicle().getCustomer().getName();
        this.modelName = repair.getVehicle().getModel() != null 
                ? repair.getVehicle().getModel().getName() 
                : null;
        this.createdAt = repair.getCreatedAt();
    }
}