package com.fixit.server.domain.vehicle;

import com.fixit.server.domain.customer.Customer;
import com.fixit.server.domain.customer.CustomerRepository;
import com.fixit.server.domain.vehiclemodel.VehicleModel;
import com.fixit.server.domain.vehiclemodel.VehicleModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;
    private final VehicleModelRepository vehicleModelRepository;

    @Transactional
    public void register(VehicleRequestDto dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객입니다."));

        VehicleModel model = null;
        if (dto.getModelId() != null) {
            model = vehicleModelRepository.findById(dto.getModelId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 차종입니다."));
        }

        Vehicle vehicle = Vehicle.builder()
                .customer(customer)
                .model(model)
                .plateNumber(dto.getPlateNumber())
                .lastMileage(dto.getLastMileage())
                .build();

        vehicleRepository.save(vehicle);
    }

    @Transactional(readOnly = true)
    public Optional<VehicleResponseDto> findByPlateNumber(String plateNumber) {
        return vehicleRepository.findByPlateNumber(plateNumber)
                .map(VehicleResponseDto::new);
    }
}