package com.edu.icesi.LibraryManagement.controller;

import com.edu.icesi.LibraryManagement.dao.request.SignInRequest;
import com.edu.icesi.LibraryManagement.dao.request.SignUpRequest;
import com.edu.icesi.LibraryManagement.dao.response.JwtAuthenticationResponse;
import com.edu.icesi.LibraryManagement.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.icesi.LibraryManagement.dao.request.SignUpRequest;
import com.edu.icesi.LibraryManagement.dao.request.SignInRequest;
import com.edu.icesi.LibraryManagement.dao.response.JwtAuthenticationResponse;
import com.edu.icesi.LibraryManagement.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}