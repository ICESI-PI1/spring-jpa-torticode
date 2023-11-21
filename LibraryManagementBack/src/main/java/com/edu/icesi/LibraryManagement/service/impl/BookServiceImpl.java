package com.edu.icesi.LibraryManagement.service.impl;

import com.edu.icesi.LibraryManagement.persistence.model.Author;
import com.edu.icesi.LibraryManagement.persistence.model.Book;
import com.edu.icesi.LibraryManagement.persistence.repository.IBookRepository;
import com.edu.icesi.LibraryManagement.service.dto.AuthorBookDTO;
import com.edu.icesi.LibraryManagement.service.IBookService;
import com.edu.icesi.LibraryManagement.service.dto.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {

    private IBookRepository bookRepository;
    private Mapper mapper;

    public BookServiceImpl(IBookRepository bookRepository, Mapper mapper){
        this.bookRepository=bookRepository;
        this.mapper = mapper;
    }
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Boolean uploadBook(Long id, Book book) {
        Optional<Book> existingBookOptional = findById(id);

        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();

            // Actualizar los campos del libro con los valores proporcionados
            existingBook.setTitle(book.getTitle());
            existingBook.setPublicationDate(book.getPublicationDate());
            existingBook.setAuthor(book.getAuthor());

            // Guardar el libro actualizado en la base de datos
            bookRepository.save(existingBook);

            return true; // Indica que la actualización fue exitosa
        } else {
            throw new NoSuchElementException("No se encontró el libro con el ID proporcionado: " + id);
        }
    }


    @Override
    public Boolean deleteBook(Long id) {
        Optional<Book> book = findById(id);
        boolean flag = false;

        if (book.isPresent()) {
            bookRepository.delete(book.get());
            flag = true;
        } else {
            throw new NoSuchElementException("No book found with the given ID: " + id);
        }
        return flag;
    }
    @Override
    public List<AuthorBookDTO> getBooksbyAuthor(Long idAuthor) {
        return getAllBooks().stream()
                .filter(book -> book.getAuthor().getId().equals(idAuthor))
                .map(book -> {
                    Author author = book.getAuthor();
                    AuthorBookDTO dto = new AuthorBookDTO();

                    dto.setAuthorName(author.getName());
                    dto.setAuthorNationality(author.getNationality());

                    dto.setBookTitle(book.getTitle());
                    dto.setBookPublicationDate(book.getPublicationDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
