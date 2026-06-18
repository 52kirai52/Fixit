package com.fixit.server.domain.user;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 영문 소문자로 시작, 영문+숫자, _ 허용, 4~20자
    @Column(nullable = false, unique = true, length = 20)
    private String username;

    // 최소 8자, 영문+숫자+특수문자 필수, 공백 불가
    @Column(nullable = false, length = 255)
    private String password;

    // 한글/영문만 허용, 공백 허용, 2~100자
    @Column(nullable = false, length = 100)
    private String name;

    // 숫자만, 국내 번호만 지원, 11자
    @Column(nullable = false, length = 11)
    private String phone;

    @Builder.Default
    @Column(nullable = false)
    private Boolean phoneVerified = false;

    // 이메일 형식 필수, 255자 이하
    @Column(nullable = false, length = 255)
    private String email;

    @Builder.Default
    @Column(nullable = false)
    private Boolean emailVerified = false;

    @Column(updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;

}