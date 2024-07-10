package com.redvinca.assignment.ecom_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redvinca.assignment.ecom_backend.request.LoginRequest;
import com.redvinca.assignment.ecom_backend.request.RegisterRequest;
import com.redvinca.assignment.ecom_backend.response.LoginRegisterResponse;
import com.redvinca.assignment.ecom_backend.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/registerLogin")
public class LoginRegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("signUp")
    public LoginRegisterResponse registerUser(@RequestBody @Valid RegisterRequest registerRequest) {  
        return userService.registerUser(registerRequest) ;
    }
    @PostMapping("/signIn")
    public LoginRegisterResponse loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }
}
