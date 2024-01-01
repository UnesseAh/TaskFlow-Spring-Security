package com.taskflow.example.controller;

import com.taskflow.example.dto.request.SignUpRequest;
import com.taskflow.example.model.AppUser;
import com.taskflow.example.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.register(signUpRequest));
    }

//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
//        return ResponseEntity.ok(authenticationService.authenticate(request));
//
//    }
}
