package com.fixit.server.domain.repair;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    List<Repair> findByShopIdAndStatus(Long shopId, String status);
    List<Repair> findByShopId(Long shopId);
}