package com.springsecurity.controller;


import com.springsecurity.dto.LoginRequest;
import com.springsecurity.dto.SignUpRequest;

import com.springsecurity.dto.JwtResponse;
import com.springsecurity.service.UserService;
import com.springsecurity.util.HttpResponseBuilder;
import com.springsecurity.util.JWTOauthTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
public class AuthController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @Autowired
    JWTOauthTokenUtil jwtOauthTokenUtil;

    @PostMapping("/signup")
    public ResponseEntity userSignUp(@Valid  @RequestBody SignUpRequest request)
    {

        userService.registerUser(request);
        return ResponseEntity.ok(new HttpResponseBuilder().message("success").data("User registered successfully").build());
    }

    @PostMapping("/signin")
    public ResponseEntity userSignIn(@Valid @RequestBody LoginRequest loginRequest)
    {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtOauthTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }



}
