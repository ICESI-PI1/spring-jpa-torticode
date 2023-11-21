package com.edu.icesi.LibraryManagement.service.dto;

import com.edu.icesi.LibraryManagement.persistence.model.Author;
import com.edu.icesi.LibraryManagement.persistence.model.Book;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Mapper {

    public BookDTO toDTObook(Book book){
        String title = book.getTitle();
        LocalDate publicationDate = book.getPublicationDate();
        AuthorDTO authorDTO = toDTOauthor(book.getAuthor());
        return new BookDTO(title,publicationDate,authorDTO);
    }

    public AuthorDTO toDTOauthor(Author author){
        String name = author.getName();
        String nationality = author.getNationality();
        return new AuthorDTO(name,nationality);
    }
    public Author toAuthor(AuthorDTO authorDTO){
        return new Author(authorDTO.getName(),authorDTO.getNationality());
    }
    public Book toBook(BookDTO bookDTO){
        return new Book(bookDTO.getTitle(),bookDTO.getPublicationDate(),toAuthor(bookDTO.getAuthorDTO()));
    }
}
