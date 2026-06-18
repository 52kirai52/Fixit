package com.fixit.server.domain.auth;

import com.fixit.server.domain.auth.requestDto.LoginRequestDto;
import com.fixit.server.domain.auth.requestDto.RegisterRequestDto;
import com.fixit.server.domain.auth.responseDto.LoginResponseDto;
import com.fixit.server.domain.auth.responseDto.LoginServiceResultDto;
import com.fixit.server.domain.shop.Shop;
import com.fixit.server.domain.shop.ShopRepository;
import com.fixit.server.domain.user.User;
import com.fixit.server.domain.user.UserRepository;
import com.fixit.server.global.jwt.JwtUtil;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, String> redisTemplate;


    @Transactional(readOnly = true)
    public LoginServiceResultDto login(LoginRequestDto dto) {
        // 1. 아이디로 유저 조회
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        // 2. 비밀번호 일치 확인
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 유저의 사업체 조회
        Shop shop = shopRepository.findByOwnerId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("사업체를 먼저 등록해주세요."));

        // 4. 토큰 발급 후 반환
        String accessToken = jwtUtil.generateToken(user.getUsername(), shop.getId());
        String refreshToken = java.util.UUID.randomUUID().toString();

        // 5. Refresh Token Memurai 저장
        redisTemplate.opsForValue().set(
            "refresh:" + user.getUsername(),
            refreshToken,
            7,
            TimeUnit.DAYS
        );

        // 6. Access Token 및 유저 정보 응답 객체 생성
        LoginResponseDto loginResponseDto = new LoginResponseDto(accessToken, user.getUsername(), shop.getName());

        // 7. Refresh Token + 응답 객체 반환
        return new LoginServiceResultDto(refreshToken, loginResponseDto);
    }

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