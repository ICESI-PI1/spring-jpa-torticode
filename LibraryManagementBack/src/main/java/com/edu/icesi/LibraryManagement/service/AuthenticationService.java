package com.edu.icesi.LibraryManagement.service;

import com.edu.icesi.LibraryManagement.dao.request.SignInRequest;
import com.edu.icesi.LibraryManagement.dao.request.SignUpRequest;
import com.edu.icesi.LibraryManagement.dao.response.JwtAuthenticationResponse;
import com.edu.icesi.LibraryManagement.persistence.model.User;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);


    JwtAuthenticationResponse signin(SignInRequest request);

}
