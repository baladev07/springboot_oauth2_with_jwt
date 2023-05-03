package com.springsecurity.service;

import com.springsecurity.model.UserEntity;

import java.util.List;

public interface UserService {

    public void addUser(UserEntity userEntity);

    UserEntity findUserByEmailId(String email);

    List<UserEntity> getAllUsers();

    void updateUser(UserEntity userEntity);

    void deleteUser(UserEntity userEntity);


}
