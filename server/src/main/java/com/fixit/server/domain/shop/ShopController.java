package com.fixit.server.domain.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shops")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @AuthenticationPrincipal String username,
            @RequestBody ShopRequestDto dto) {
        shopService.register(username, dto);
        return ResponseEntity.ok("사업체 등록 성공");
    }
}