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
public class SignUpRequest {
    private Long id;
    private String username;
    private String password;
    private Rol rol;
}