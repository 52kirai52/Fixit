package com.fixit.server.domain.vehiclemodel;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
        vehicleModelService.register(1L, dto);
        return ResponseEntity.ok("차종 등록 성공");
    }

    @GetMapping
    public ResponseEntity<List<VehicleModelResponseDto>> findAll(
            @AuthenticationPrincipal String username) {
        List<VehicleModelResponseDto> result = vehicleModelService.findAll(1L)
                .stream()
                .map(VehicleModelResponseDto::new)
                .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok(result);
    }
}