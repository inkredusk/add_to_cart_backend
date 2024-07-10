package com.redvinca.assignment.ecom_backend.serviceimpl;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redvinca.assignment.ecom_backend.model.User;
import com.redvinca.assignment.ecom_backend.repository.UserRepository;
import com.redvinca.assignment.ecom_backend.request.LoginRequest;
import com.redvinca.assignment.ecom_backend.request.RegisterRequest;
import com.redvinca.assignment.ecom_backend.response.LoginRegisterResponse;
import com.redvinca.assignment.ecom_backend.service.UserService;
import com.redvinca.assignment.ecom_backend.util.AESUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public LoginRegisterResponse registerUser(RegisterRequest registerRequest) {
	    LoginRegisterResponse response = new LoginRegisterResponse();
	    
	    try {
	        // Check if user with the same email already exists
	        if (userRepository.findByEmail(registerRequest.getEmail()) != null) {
	            response.setMessage("This email is already registered...!.");
	            return response;
	        }
	        User user = new User();
	        user.setFirstName(registerRequest.getFirstName());
	        user.setLastName(registerRequest.getLastName());
	        user.setAddress(registerRequest.getAddress());
	        user.setCountryName(registerRequest.getCountry()); // Adjust this line if the country is dynamic or passed in the request
	        user.setCityName(registerRequest.getCity());
	        user.setStateName(registerRequest.getState());
	        user.setPincode(registerRequest.getPincode());
	        user.setEmail(registerRequest.getEmail());
	        user.setMobileNumber(registerRequest.getMobileNumber());
	        
	        // Encrypt password
	        SecretKey secretKey = AESUtils.getStoredKey();
	        log.info(" SecretKey--at registation :-"+secretKey);
	        String encryptedPassword = AESUtils.encrypt(registerRequest.getPassword(), secretKey);
	        user.setPassword(encryptedPassword);
	        user.setConformPassword(registerRequest.getConformPassword()); // Consider whether you need to store this

	        userRepository.save(user);
	        response.setMessage("User registered successfully..!");
	    } catch (Exception e) {
	        response.setMessage("Error registering user");
	    }
	    return response;
	}

	@Override
	public LoginRegisterResponse loginUser(LoginRequest loginRequest) {
	    // Fetch the user using the email from the loginRequest
	    User user = userRepository.findByEmail(loginRequest.getEmail());
	    System.out.println("--user--" + user);

	    // Create a response object
	    LoginRegisterResponse response = new LoginRegisterResponse();

	    // Check if the user exists
	    if (user == null) {
	        response.setMessage("Invalid username/password");
	        return response;
	    }

	    try {
	        // Retrieve the stored secret key
	        SecretKey secretKey = AESUtils.getStoredKey();
	        log.info("Check SecretKey--at registation :-"+secretKey);

	        // Decrypt the stored password
	        String decryptedPassword = AESUtils.decrypt(user.getPassword(), secretKey);
	        log.info("Check DecryptedPassword--at Login :-"+decryptedPassword);
	      

	        // Validate the password
	        if (decryptedPassword.equals(loginRequest.getPassword())) {
	            response.setMessage("Login successful!");
	        } else {
	            response.setMessage("Invalid username/password");
	        }
	    } catch (Exception e) {
	        response.setMessage("Error logging in");
	    }

	    return response;
	}

}
