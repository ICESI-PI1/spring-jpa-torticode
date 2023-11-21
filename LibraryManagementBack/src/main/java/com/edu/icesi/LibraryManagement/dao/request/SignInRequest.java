package com.edu.icesi.LibraryManagement.dao.request;

import com.edu.icesi.LibraryManagement.persistence.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
    private String username;
    private String password;

    private Long id;
    private Rol rol;



}