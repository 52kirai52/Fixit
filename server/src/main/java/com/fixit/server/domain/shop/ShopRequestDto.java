package com.fixit.server.domain.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShopRequestDto {
    private String name;
    private String address;
}