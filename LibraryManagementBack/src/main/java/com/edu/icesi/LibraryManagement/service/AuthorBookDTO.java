package com.edu.icesi.LibraryManagement.service;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter @Getter
public class AuthorBookDTO {
    private Long authorId;
    private String authorName;
    private String authorNationality;

    private Long bookId;
    private String bookTitle;
    private LocalDate bookPublicationDate;

}
