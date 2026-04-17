package com.fixit.server.domain.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerRequestDto {
    private String name;
    private String phone;
}