package com.fixit.server.domain.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.fixit.server.global.jwt.ShopIdExtractor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> register(
            @AuthenticationPrincipal String username,
            @RequestBody CustomerRequestDto dto) {
        customerService.register(ShopIdExtractor.extract(), dto);
        return ResponseEntity.ok("고객 등록 성공");
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @AuthenticationPrincipal String username,
            @RequestParam String phone) {
        return customerService.findByPhone(ShopIdExtractor.extract(), phone)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}