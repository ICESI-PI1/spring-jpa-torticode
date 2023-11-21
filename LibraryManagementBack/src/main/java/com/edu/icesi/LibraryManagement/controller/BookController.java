package com.edu.icesi.LibraryManagement.controller;

import com.edu.icesi.LibraryManagement.persistence.model.Author;
import com.edu.icesi.LibraryManagement.persistence.model.Book;
import com.edu.icesi.LibraryManagement.service.dto.AuthorBookDTO;
import com.edu.icesi.LibraryManagement.service.IBookService;
import com.edu.icesi.LibraryManagement.service.dto.AuthorDTO;
import com.edu.icesi.LibraryManagement.service.dto.BookDTO;
import com.edu.icesi.LibraryManagement.service.dto.Mapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5175/")
@RequestMapping("/libros")
public class BookController {

    private final IBookService bookService;
    private final Mapper mapper;
    public BookController(IBookService bookService, Mapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping("")
    public List<BookDTO> showAllBooks(){
        return bookService.getAllBooks().stream().map(mapper::toDTObook)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDTO showBook(@PathVariable Long id, Model model){
        Book book = bookService.findById(id).orElse(null);
        BookDTO bookDTO = mapper.toDTObook(book);
        return bookDTO;
    }
    @PostMapping("")
    public BookDTO createBook(@RequestBody BookDTO newBookDTO){
        Book newBook = mapper.toBook(newBookDTO);
        Book savedBook = bookService.saveBook(newBook);
        BookDTO savedBookDTO = mapper.toDTObook(savedBook);
        return savedBookDTO;
    }
    @PutMapping("/{id}")
    public Boolean uploadBook(@PathVariable Long id, @RequestBody BookDTO uploadBookDTO){
        Book existingBook = bookService.findById(id).orElse(null);
        if (existingBook != null) {
            Book updatedBook = mapper.toBook(uploadBookDTO);
            return bookService.uploadBook(id, updatedBook);
        }else{
            return false;
        }
    }

    @DeleteMapping("/{id}")
    public Boolean deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id);
    }

    @GetMapping("/autor/{idAutor}")
    public List<AuthorBookDTO> showBooksByAuthor(@PathVariable Long idAutor){
        return bookService.getBooksbyAuthor(idAutor);
    }

}
