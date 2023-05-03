package com.springsecurity.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtResponse extends BaseEntity {
    private String jwtToken;

    public JwtResponse(String jwtToken)
    {
        this.jwtToken = jwtToken;
    }

}
