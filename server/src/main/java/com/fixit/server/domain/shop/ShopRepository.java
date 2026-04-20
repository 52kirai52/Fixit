package com.fixit.server.domain.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByOwnerId(Long ownerId);
}