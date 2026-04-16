package com.fixit.server.domain.shop;

import com.fixit.server.domain.user.User;
import com.fixit.server.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    @Transactional
    public void register(String username, ShopRequestDto dto) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Shop shop = Shop.builder()
                .owner(user)
                .name(dto.getName())
                .address(dto.getAddress())
                .build();

        shopRepository.save(shop);
    }
}