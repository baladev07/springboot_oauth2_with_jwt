package com.springsecurity.service.impl;

import com.springsecurity.model.UserEntity;
import com.springsecurity.repository.UserRepository;
import com.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired private UserRepository userRepository;


    @Override
    @Transactional
    public void addUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findUserByEmailId(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }
}


