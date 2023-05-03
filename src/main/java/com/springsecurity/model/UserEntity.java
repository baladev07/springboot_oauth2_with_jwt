package com.springsecurity.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;


@Table
@Entity
@Getter
@Setter
public class UserEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int user_id;


    @Column
    private String name;


    @Column
    @JsonIgnore
    private String password;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name= "user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    @JsonManagedReference
    private RoleEntity roleEntity;



    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private DepartmentEntity departmentEntity;


    @Column
    private String email;

    public void setPassword(String password)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);

    }
}
