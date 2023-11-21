package com.edu.icesi.LibraryManagement.controller;

import com.edu.icesi.LibraryManagement.persistence.model.Book;
import com.edu.icesi.LibraryManagement.service.IBookService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/libros")
public class BookController {

    private final IBookService bookService;
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public List<Book> showAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book showBook(@PathVariable Long id, Model model){
        return bookService.findById(id).orElse(null);
    }
    @PostMapping("")
    public Book createAuthor(@RequestBody Book newBook){
        return bookService.saveBook(newBook);
    }
    @PutMapping("/{id}")
    public Boolean uploadBook(@PathVariable Long id, @RequestBody Book uploadBook){
        return bookService.uploadBook(id,uploadBook);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id);
    }

}
