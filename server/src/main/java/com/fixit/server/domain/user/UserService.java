package com.fixit.server.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fixit.server.domain.shop.Shop;
import com.fixit.server.domain.shop.ShopRepository;
import com.fixit.server.domain.user.ResponseDto.GetMeResponseDto;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    
    @Transactional(readOnly = true)
    public GetMeResponseDto getMe(String username) {
        // 1. 토큰에서 받은 username으로 유저 조회
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 2. 유저의 사업체 조회
        Shop shop = shopRepository.findByOwnerId(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("사업체를 먼저 등록해주세요."));

        // 3. 토큰 없이 정보만 반환 (조회용)
        return new GetMeResponseDto(user.getUsername(), shop.getName());
    }

}