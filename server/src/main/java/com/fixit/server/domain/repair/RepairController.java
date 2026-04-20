package com.fixit.server.domain.repair;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.fixit.server.global.jwt.ShopIdExtractor;

import java.util.List;

@RestController
@RequestMapping("/api/repairs")
@RequiredArgsConstructor
public class RepairController {

    private final RepairService repairService;

    @PostMapping
    public ResponseEntity<String> register(
            @AuthenticationPrincipal String username,
            @RequestBody RepairRequestDto dto) {
        repairService.register(ShopIdExtractor.extract(), dto);
        return ResponseEntity.ok("정비 접수 성공");
    }

    @GetMapping
    public ResponseEntity<List<RepairResponseDto>> findAll(
            @AuthenticationPrincipal String username) {
        return ResponseEntity.ok(repairService.findAll(ShopIdExtractor.extract()));
    }

    @GetMapping("/status")
    public ResponseEntity<List<RepairResponseDto>> findByStatus(
            @AuthenticationPrincipal String username,
            @RequestParam String status) {
        return ResponseEntity.ok(repairService.findByStatus(ShopIdExtractor.extract(), status));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        repairService.updateStatus(id, status);
        return ResponseEntity.ok("상태 변경 성공");
    }
}