package com.edu.icesi.LibraryManagement.persistence.repository;

import com.edu.icesi.LibraryManagement.persistence.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book,Long> {
}
