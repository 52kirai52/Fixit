package com.fixit.server.domain.vehiclemodel;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.fixit.server.global.jwt.ShopIdExtractor;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-models")
@RequiredArgsConstructor
public class VehicleModelController {

    private final VehicleModelService vehicleModelService;

    @PostMapping
    public ResponseEntity<String> register(
            @AuthenticationPrincipal String username,
            @RequestBody VehicleModelRequestDto dto) {
        vehicleModelService.register(ShopIdExtractor.extract(), dto);
        return ResponseEntity.ok("차종 등록 성공");
    }

    @GetMapping
    public ResponseEntity<List<VehicleModelResponseDto>> findAll(
            @AuthenticationPrincipal String username) {
        return ResponseEntity.ok(vehicleModelService.findAll(ShopIdExtractor.extract()));
    }
}