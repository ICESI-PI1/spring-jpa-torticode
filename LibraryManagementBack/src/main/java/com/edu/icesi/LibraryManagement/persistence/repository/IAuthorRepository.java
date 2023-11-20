package com.edu.icesi.LibraryManagement.persistence.repository;

import com.edu.icesi.LibraryManagement.persistence.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorRepository extends JpaRepository<Author,Long> {
}
