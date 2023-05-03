package com.springsecurity.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;


@RequiredArgsConstructor
@Data
public class JwtRequest extends BaseEntity{

    @Column
    private String username;


    @Column
    private String password;

}
