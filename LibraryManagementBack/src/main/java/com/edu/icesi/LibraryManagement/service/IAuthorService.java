package com.edu.icesi.LibraryManagement.service;

import com.edu.icesi.LibraryManagement.persistence.model.Author;
import com.edu.icesi.LibraryManagement.persistence.model.Book;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    List<Author> getAllAuthors();
    Optional<Author> findById(Long id);
    Author saveAuthor(Author author);
    Boolean uploadAuthor(Long id, Author author);
    Boolean deleteAuthor(Long id);
}
