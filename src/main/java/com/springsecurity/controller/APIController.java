package com.springsecurity.controller;


import com.springsecurity.model.UserEntity;
import com.springsecurity.service.UserService;
import com.springsecurity.util.HttpResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {
   @Autowired private UserService userService;

    @GetMapping("/users")
    public ResponseEntity getUsers()
    {
        List<UserEntity> userEntityList = userService.getAllUsers();

        return ResponseEntity.ok(HttpResponseHandler.buildResponse("success",new HashMap<String, List>(){{put("Users",userService.getAllUsers());}}));

    }
}
