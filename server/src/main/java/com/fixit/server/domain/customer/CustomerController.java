package com.fixit.server.domain.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> register(
            @AuthenticationPrincipal String username,
            @RequestBody CustomerRequestDto dto) {
        // 나중에 username으로 shopId 찾는 로직 추가 예정
        customerService.register(1L, dto);
        return ResponseEntity.ok("고객 등록 성공");
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @AuthenticationPrincipal String username,
            @RequestParam String phone) {
        return customerService.findByPhone(1L, phone)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}