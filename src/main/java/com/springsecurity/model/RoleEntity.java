package com.springsecurity.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@RequiredArgsConstructor
@Getter
@Setter
public class RoleEntity extends BaseEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int roleID;

    public static String ROLE_ADMIN = "role_admin";

    public static String ROLE_USER = "role_user";

    @Column
    private String name;

    @OneToMany(mappedBy = "roleEntity",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserEntity> userEntities;

    public RoleEntity(String name)
    {
        this.name= name;
    }
}
