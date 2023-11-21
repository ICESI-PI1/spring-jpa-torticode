package com.edu.icesi.LibraryManagement.service.impl;

import com.edu.icesi.LibraryManagement.dao.response.JwtAuthenticationResponse;
import com.edu.icesi.LibraryManagement.service.IJwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.icesi.LibraryManagement.persistence.model.Rol;
import com.edu.icesi.LibraryManagement.persistence.model.User;
import com.edu.icesi.LibraryManagement.persistence.repository.IUserRepository;
import com.edu.icesi.LibraryManagement.service.AuthenticationService;
import com.edu.icesi.LibraryManagement.service.IJwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRol(Rol.USER);
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        var existingUser = userRepository.findByEmail(user.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(existingUser);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }


}