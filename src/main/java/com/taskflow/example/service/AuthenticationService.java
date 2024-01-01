package com.taskflow.example.service;


import com.taskflow.example.dto.authentication.RegisterRequest;
import com.taskflow.example.dto.authentication.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(RegisterRequest request);
}
