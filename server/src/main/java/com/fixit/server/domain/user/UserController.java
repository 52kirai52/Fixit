package com.fixit.server.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fixit.server.domain.user.RequestDto.LoginRequestDto;
import com.fixit.server.domain.user.ResponseDto.GetMeResponseDto;
import com.fixit.server.domain.user.ResponseDto.LoginResponseDto;
import com.fixit.server.domain.user.ResponseDto.LoginServiceResultDto;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
        summary = "유저 로그인",
        description = """
            1. 아이디로 유저 조회
            2. 비밀번호 일치 확인
            3. 유저의 사업체 조회
            4. Access Token 발급
            5. Refresh Token 생성 및 Memurai 저장
            6. Refresh Token 쿠키에 담아 응답
            7. Refresh Token + 응답 객체 반환
            """
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto, HttpServletResponse response) {
        LoginServiceResultDto result = userService.login(dto);

        // Refresh Token 쿠키에 담기
        Cookie cookie = new Cookie("refreshToken", result.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(result.getLoginResponseDto());
    }

    @Operation(
        summary = "유저 정보 확인(새로고침으로 인한 정보 삭제 방지)",
        description = """
            1. 토큰에서 받은 username으로 유저 조회
            2. 유저의 사업체 조회
            3. 토큰 없이 정보만 반환(조회용)
            """
    )
    @GetMapping("/me")
    public ResponseEntity<GetMeResponseDto> me(@AuthenticationPrincipal String username) {
        GetMeResponseDto response = userService.getMe(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("인증된 사용자입니다.");
    }
}