package com.edu.icesi.LibraryManagement.controller;


import com.edu.icesi.LibraryManagement.persistence.model.Author;
import com.edu.icesi.LibraryManagement.persistence.model.Book;
import com.edu.icesi.LibraryManagement.service.dto.AuthorBookDTO;
import com.edu.icesi.LibraryManagement.service.IAuthorService;
import com.edu.icesi.LibraryManagement.service.IBookService;
import com.edu.icesi.LibraryManagement.service.dto.AuthorDTO;
import com.edu.icesi.LibraryManagement.service.dto.Mapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5175/")
@RequestMapping("/autores")
public class AuthorController {

    private final IAuthorService authorService;
    private final IBookService bookService;

    private final Mapper mapper;

    public AuthorController(IAuthorService authorService, IBookService bookService, Mapper mapper) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.mapper = mapper;

        /*
        Author author1= new Author(1L,"Carlos","Espanya");
        Author author2= new Author(2L,"Victor","Espanya");
        Author author3= new Author(3L,"Danna","Colombia");

        Book book1 = new Book(1L,"Método dorfman", LocalDate.of(2003,02,26),author1);
        Book book2 = new Book(2L,"Casos de uso",LocalDate.of(2007,06,17),author1);
        Book book3 = new Book(3L,"Steven Universe", LocalDate.of(2014,10,02),author1);
        Book book4 = new Book(4L,"Miraculous",LocalDate.of(2016,12,03),author1);

        authorService.saveAuthor(author1);
        authorService.saveAuthor(author2);
        authorService.saveAuthor(author3);

        bookService.saveBook(book1);
        bookService.saveBook(book2);
        bookService.saveBook(book3);
        bookService.saveBook(book4);

         */
    }
    @CrossOrigin(origins = "*")
    @GetMapping("")
    public List<AuthorDTO> showAllAuthor(){
        return authorService.getAllAuthors().stream().map(mapper::toDTOauthor)
                .collect(Collectors.toList());
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public AuthorDTO showAuthor(@PathVariable Long id, Model model){
        Author author = authorService.findById(id).orElse(null);
        AuthorDTO authorDTO = mapper.toDTOauthor(author);
        return authorDTO;
    }
    @CrossOrigin(origins = "*")
    @PostMapping("")
    public AuthorDTO createAuthor(@RequestBody AuthorDTO newAuthorDTO){
        Author newAuthor = mapper.toAuthor(newAuthorDTO);
        Author savedAuthor = authorService.saveAuthor(newAuthor);
        AuthorDTO savedAuthorDTO = mapper.toDTOauthor(savedAuthor);
        return savedAuthorDTO;
    }

    @PutMapping("/{id}")
    public Boolean uploadAuthor(@PathVariable Long id, @RequestBody AuthorDTO uploadAuthorDTO){
        Author existingAuthor = authorService.findById(id).orElse(null);
        if (existingAuthor != null) {
            Author updatedAuthor = mapper.toAuthor(uploadAuthorDTO);
            return authorService.uploadAuthor(id, updatedAuthor);
        }else{
            return false;
        }
    }
    @DeleteMapping("/{id}")
    public Boolean deleteAuthor(@PathVariable Long id){
        return authorService.deleteAuthor(id);
    }

    @GetMapping("/{idAuthor}/libros")
    public List<AuthorBookDTO> showBooksByAuthor(@PathVariable Long idAuthor){
        return bookService.getBooksbyAuthor(idAuthor);
    }


}
