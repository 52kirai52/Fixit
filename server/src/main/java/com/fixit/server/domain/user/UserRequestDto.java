package com.fixit.server.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    private String username;
    private String password;
    private String name;
    private String phone;
}