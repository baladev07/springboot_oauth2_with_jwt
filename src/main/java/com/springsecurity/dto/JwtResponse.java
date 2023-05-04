package com.springsecurity.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtResponse {
    private String access_token;

    private String refresh_token;

    public JwtResponse(String accessToken)
    {
        this.access_token = accessToken;
    }

}
