package com.redvinca.assignment.ecom_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redvinca.assignment.ecom_backend.request.ChangePasswordRequest;
import com.redvinca.assignment.ecom_backend.request.LoginRequest;
import com.redvinca.assignment.ecom_backend.request.RegisterRequest;
import com.redvinca.assignment.ecom_backend.response.LoginRegisterResponse;
import com.redvinca.assignment.ecom_backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("${user.api.url}")
public class LoginRegistrationController {

    @Autowired
    private UserService userService;

    @Operation(summary = "This api is used to register the user..")
    @PostMapping("${user.api.signUp}")
    public LoginRegisterResponse registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        return userService.registerUser(registerRequest);
    }

    @Operation(summary = "This API is used to log in the user.")
    @PostMapping("${user.api.signIn}")
    public LoginRegisterResponse loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }

    @Operation(summary = "This API is used to log out the user.")
    @PostMapping("${user.api.logout}")
    public LoginRegisterResponse logout(HttpSession session) {
        return userService.logoutUser(session);
    }

    @Operation(summary = "This API is used to change the Password of user.")
    @PostMapping("${user.api.changePassword}")
    public LoginRegisterResponse changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        return userService.changePassword(changePasswordRequest);
    }

}
