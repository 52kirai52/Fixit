package com.fixit.server.domain.auth.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private String accesstoken;
    private String username;
}