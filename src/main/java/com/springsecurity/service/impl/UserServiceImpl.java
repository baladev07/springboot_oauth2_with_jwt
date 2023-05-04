package com.springsecurity.service.impl;

import com.springsecurity.dto.SignUpRequest;
import com.springsecurity.exception.UserAlreadyExistsException;
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


    @org.springframework.transaction.annotation.Transactional(value="transactionManager")
    public void registerUser(final SignUpRequest request) throws UserAlreadyExistsException
    {
        if(userRepository.existsByEmailIgnoreCase(request.getEmail()))
        {
            throw new UserAlreadyExistsException("email already exists");
        }
        UserEntity userEntity = new UserEntity(request.getUsername(),request.getPassword(),request.getEmail());
        userRepository.save(userEntity);
        userRepository.flush();
    }
}


