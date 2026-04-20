package com.fixit.server.global.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ShopIdExtractor {

    public static Long extract() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getCredentials() == null) {
            throw new IllegalStateException("인증 정보가 없습니다.");
        }
        return (Long) authentication.getCredentials();
    }
}