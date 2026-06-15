package com.fixit.server.domain.user.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetMeResponseDto {
    private String username;
    private String shopName;
}
