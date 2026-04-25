package com.fixit.server.domain.auth;

import com.fixit.server.domain.shop.Shop;
import com.fixit.server.domain.shop.ShopRepository;
import com.fixit.server.domain.user.User;
import com.fixit.server.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterRequestDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .phone(dto.getPhone())
                .build();
        userRepository.save(user);

        Shop shop = Shop.builder()
                .owner(user)
                .name(dto.getShopName())
                .address(dto.getShopAddress())
                .build();
        shopRepository.save(shop);
    }
}