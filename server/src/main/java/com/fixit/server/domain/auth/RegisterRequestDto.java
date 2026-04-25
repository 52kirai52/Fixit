package com.fixit.server.domain.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequestDto {
    private String username;
    private String password;
    private String name;
    private String phone;
    private String shopName;
    private String shopAddress;
}