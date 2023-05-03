package com.springsecurity.service.impl;

import com.springsecurity.model.UserEntity;
import com.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByName(username);
        if(userEntity !=null)
        {
            return new User(userEntity.getName(), userEntity.getPassword(),new ArrayList());
        }
        else {
            throw new UsernameNotFoundException("User not found for this user name"+username);
        }
    }
}
