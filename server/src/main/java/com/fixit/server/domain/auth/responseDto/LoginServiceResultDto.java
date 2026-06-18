package com.fixit.server.domain.auth.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginServiceResultDto {
    private String refreshToken;
    private LoginResponseDto loginResponseDto;
}
