package com.fixit.server.domain.vehicle;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<String> register(
            @AuthenticationPrincipal String username,
            @RequestBody VehicleRequestDto dto) {
        vehicleService.register(dto);
        return ResponseEntity.ok("차량 등록 성공");
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @AuthenticationPrincipal String username,
            @RequestParam String plateNumber) {
        return vehicleService.findByPlateNumber(plateNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}