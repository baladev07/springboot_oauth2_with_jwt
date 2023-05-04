package com.springsecurity.dto;


import com.springsecurity.validator.PasswordValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@PasswordValidator
public class SignUpRequest {

    private String username;

    private String email;

    private String password;

    private String matchingPassword;


}
