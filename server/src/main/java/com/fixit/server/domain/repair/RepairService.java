package com.fixit.server.domain.repair;

import com.fixit.server.domain.shop.Shop;
import com.fixit.server.domain.shop.ShopRepository;
import com.fixit.server.domain.vehicle.Vehicle;
import com.fixit.server.domain.vehicle.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepairService {

    private final RepairRepository repairRepository;
    private final ShopRepository shopRepository;
    private final VehicleRepository vehicleRepository;

    @Transactional
    public void register(Long shopId, RepairRequestDto dto) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 업체입니다."));

        Vehicle vehicle = null;

        if (dto.getVehicleId() != null) {
            vehicle = vehicleRepository.findById(dto.getVehicleId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 차량입니다."));
        } else if (dto.getPlateNumber() != null) {
            vehicle = vehicleRepository.findByPlateNumber(dto.getPlateNumber())
                    .orElse(null);
        }

        Repair repair = Repair.builder()
                .shop(shop)
                .vehicle(vehicle)
                .memo(dto.getMemo())
                .build();

        repairRepository.save(repair);
    }

    @Transactional(readOnly = true)
    public List<RepairResponseDto> findAll(Long shopId) {
        return repairRepository.findByShopId(shopId)
                .stream()
                .map(RepairResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RepairResponseDto> findByStatus(Long shopId, String status) {
        return repairRepository.findByShopIdAndStatus(shopId, status)
                .stream()
                .map(RepairResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateStatus(Long repairId, String status) {
        Repair repair = repairRepository.findById(repairId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 정비 건입니다."));
        repair.updateStatus(status);
    }
}