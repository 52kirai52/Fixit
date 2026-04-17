package com.fixit.server.domain.vehiclemodel;

import com.fixit.server.domain.shop.Shop;
import com.fixit.server.domain.shop.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleModelService {

    private final VehicleModelRepository vehicleModelRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public void register(Long shopId, VehicleModelRequestDto dto) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 업체입니다."));

        VehicleModel vehicleModel = VehicleModel.builder()
                .shop(shop)
                .name(dto.getName())
                .build();

        vehicleModelRepository.save(vehicleModel);
    }

    @Transactional(readOnly = true)
    public List<VehicleModel> findAll(Long shopId) {
        return vehicleModelRepository.findByShopId(shopId);
    }
}