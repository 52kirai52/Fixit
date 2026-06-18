package com.fixit.server.domain.auth.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequestDto {
    // 영문 소문자로 시작, 영문+숫자+_ 허용, 4~20자
    @NotBlank
    @Pattern(regexp = "^[a-z][a-z0-9_]{3,19}$", message = "아이디는 영문 소문자로 시작, 4~20자여야 합니다.")
    private String username;

    // 최소 8자, 영문+숫자+특수문자 필수, 공백 불가
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*])[^\\s]{8,20}$", message = "비밀번호는 영문, 숫자, 특수문자를 포함한 8~20자여야 합니다.")
    private String password;

    // 한글/영문만 허용, 공백 허용, 2~100자
    @NotBlank
    @Pattern(regexp = "^[가-힣a-zA-Z\\s]{2,100}$", message = "이름은 한글 또는 영문 2~100자여야 합니다.")
    private String name;

    // 숫자만, 11자
    @NotBlank
    @Pattern(regexp = "^\\d{11}$", message = "전화번호는 숫자 11자리여야 합니다.")
    private String phone;

    // 이메일 형식
    @NotBlank
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
}