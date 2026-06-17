package com.fixit.server.domain.user.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginServiceResultDto {
    private String refreshToken;
    private LoginResponseDto loginResponseDto;
}
