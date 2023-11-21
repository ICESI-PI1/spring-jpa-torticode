package com.edu.icesi.LibraryManagement.service.dto;

import com.edu.icesi.LibraryManagement.persistence.model.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private String title;
    private LocalDate publicationDate;
    private AuthorDTO authorDTO;

    public BookDTO(BookDTO bookDTO) {
        this(bookDTO.getTitle(),bookDTO.getPublicationDate(),bookDTO.getAuthorDTO());
    }

}
