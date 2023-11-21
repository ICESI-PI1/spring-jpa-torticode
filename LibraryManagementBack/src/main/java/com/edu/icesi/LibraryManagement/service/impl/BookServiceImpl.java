package com.edu.icesi.LibraryManagement.service.impl;

import com.edu.icesi.LibraryManagement.persistence.model.Author;
import com.edu.icesi.LibraryManagement.persistence.model.Book;
import com.edu.icesi.LibraryManagement.persistence.repository.IBookRepository;
import com.edu.icesi.LibraryManagement.service.AuthorBookDTO;
import com.edu.icesi.LibraryManagement.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {

    private IBookRepository bookRepository;

    public BookServiceImpl(IBookRepository bookRepository){
        this.bookRepository=bookRepository;
    };
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

    @Override
    public Boolean uploadBook(Long id, Book book) {

        boolean flag = false;
        Optional<Book> oldBook = findById(id);
        if(oldBook.isPresent()){
            saveBook(book);
            flag=true;
        }else{
            throw new NoSuchElementException("No book found with the given ID: " + id);
        }

        return flag;
    }

    @Override
    public Boolean deleteBook(Long id) {

        boolean flag = false;
        Optional<Book> book = findById(id);
        if(book.isPresent()){
            flag=true;
            bookRepository.delete(book.get());
        }else{
            throw new NoSuchElementException("No book found with the given ID: " + id);
        }

        return null;
    }
    @Override
    public List<AuthorBookDTO> getBooksbyAuthor(Long idAuthor) {
        return getAllBooks().stream()
                .filter(book -> book.getAuthor().getId().equals(idAuthor))
                .map(book -> {
                    Author author = book.getAuthor();
                    AuthorBookDTO dto = new AuthorBookDTO();
                    dto.setAuthorId(author.getId());
                    dto.setAuthorName(author.getName());
                    dto.setAuthorNationality(author.getNationality());
                    dto.setBookId(book.getId());
                    dto.setBookTitle(book.getTitle());
                    dto.setBookPublicationDate(book.getPublicationDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
