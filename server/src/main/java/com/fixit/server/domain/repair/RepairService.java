package com.fixit.server.domain.repair;

import com.fixit.server.domain.customer.Customer;
import com.fixit.server.domain.customer.CustomerRepository;
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
    private final CustomerRepository customerRepository;

    @Transactional
    public void register(Long shopId, RepairRequestDto dto) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 업체입니다."));

        Vehicle vehicle = vehicleRepository.findByPlateNumber(dto.getPlateNumber())
                .orElseGet(() -> {
                    Customer customer = findOrCreateCustomer(shop, dto);
                    return vehicleRepository.save(Vehicle.builder()
                            .customer(customer)
                            .plateNumber(dto.getPlateNumber())
                            .build());
                });

        Repair repair = Repair.builder()
                .shop(shop)
                .vehicle(vehicle)
                .memo(dto.getMemo())
                .build();

        repairRepository.save(repair);
    }

    private Customer findOrCreateCustomer(Shop shop, RepairRequestDto dto) {
        if (dto.getCustomerPhone() != null && !dto.getCustomerPhone().isBlank()) {
            return customerRepository.findByPhoneAndShopId(dto.getCustomerPhone(), shop.getId())
                    .orElseGet(() -> customerRepository.save(Customer.builder()
                            .shop(shop)
                            .name(dto.getCustomerName())
                            .phone(dto.getCustomerPhone())
                            .build()));
        }

        return customerRepository.findByNameAndShopId(dto.getCustomerName(), shop.getId())
                .orElseGet(() -> customerRepository.save(Customer.builder()
                        .shop(shop)
                        .name(dto.getCustomerName())
                        .build()));
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