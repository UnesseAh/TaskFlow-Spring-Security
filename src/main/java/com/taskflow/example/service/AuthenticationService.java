package com.taskflow.example.service;


import com.taskflow.example.dto.request.SignUpRequest;
import com.taskflow.example.model.AppUser;

public interface AuthenticationService {
    AppUser register(SignUpRequest request);

//    AuthenticationResponse authenticate(AuthenticationRequest request);
}
