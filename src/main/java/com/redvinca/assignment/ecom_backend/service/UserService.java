package com.redvinca.assignment.ecom_backend.service;


import com.redvinca.assignment.ecom_backend.request.LoginRequest;
import com.redvinca.assignment.ecom_backend.request.RegisterRequest;
import com.redvinca.assignment.ecom_backend.response.LoginRegisterResponse;

public interface UserService {
	
	LoginRegisterResponse registerUser(RegisterRequest registerRequest);
    
	LoginRegisterResponse loginUser(LoginRequest loginRequest);
    
}
