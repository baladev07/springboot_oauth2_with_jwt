package com.springsecurity.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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


    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String email;


    @Column
    @JsonIgnore
    private String password;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name= "user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    @JsonManagedReference
    @JsonProperty("roles")
    private RoleEntity roleEntity;



    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JsonProperty("department_entity")
    private DepartmentEntity departmentEntity;



    public UserEntity()
    {

    }


    public void setPassword(String password)
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);

    }

    public UserEntity(String username, String password, String email)
    {

        this.name = username;
        this.email = email;
        setPassword(password);

    }


}
