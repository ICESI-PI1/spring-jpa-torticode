package com.edu.icesi.LibraryManagement.persistence.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Rol rol;

    public User(User user){
        this(user.getId(),user.getUsername(),user.getPassword(),user.getRol());
    };




}
