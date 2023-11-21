package com.edu.icesi.LibraryManagement.service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthorDTO {

    public String name;
    public String nationality;

    public AuthorDTO(AuthorDTO authorDTO){
        this(authorDTO.getName(),authorDTO.getNationality());
    }

}
