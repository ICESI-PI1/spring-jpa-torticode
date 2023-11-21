package com.edu.icesi.LibraryManagement.service.impl;

import com.edu.icesi.LibraryManagement.dao.request.SignInRequest;
import com.edu.icesi.LibraryManagement.dao.request.SignUpRequest;
import com.edu.icesi.LibraryManagement.dao.response.JwtAuthenticationResponse;
import com.edu.icesi.LibraryManagement.persistence.model.Rol;
import com.edu.icesi.LibraryManagement.service.IJwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.icesi.LibraryManagement.persistence.model.User;
import com.edu.icesi.LibraryManagement.persistence.repository.IUserRepository;
import com.edu.icesi.LibraryManagement.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;



    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        var user = User.builder()
                .id(request.getId())
                .username(request.getUsername())
                .password(encodedPassword)
                .rol(Rol.USER)
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        }catch (AuthenticationException e){
            throw new IllegalArgumentException("Invalid username or password");
        }
        var user= userRepository.findByUsername(request.getUsername()
                ).orElseThrow(() -> new IllegalArgumentException("User not found"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


}