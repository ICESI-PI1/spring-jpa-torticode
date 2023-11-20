package com.edu.icesi.LibraryManagement.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Book {

    private Long id;
    private String title;
    private LocalDate publicationDate;
    @ManyToOne
    private Author author;

    public Book(Book book){
        this(book.getId(), book.getTitle(),book.getPublicationDate(),book.getAuthor());
    }

}
