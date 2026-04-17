package com.fixit.server.domain.vehiclemodel;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {
    List<VehicleModel> findByShopId(Long shopId);
}