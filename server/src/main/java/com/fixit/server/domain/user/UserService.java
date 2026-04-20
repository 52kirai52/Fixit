package com.fixit.server.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.fixit.server.domain.shop.Shop;
import com.fixit.server.domain.shop.ShopRepository;
import com.fixit.server.global.jwt.JwtUtil;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public void register(UserRequestDto dto) {
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
    }

    @Transactional(readOnly = true)
    public String login(LoginRequestDto dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Shop shop = shopRepository.findByOwnerId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("사업체를 먼저 등록해주세요."));

        return jwtUtil.generateToken(user.getUsername(), shop.getId());
    }
}