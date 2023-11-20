package com.edu.icesi.LibraryManagement.service.impl;

import com.edu.icesi.LibraryManagement.persistence.model.Author;
import com.edu.icesi.LibraryManagement.persistence.model.Book;
import com.edu.icesi.LibraryManagement.persistence.repository.IAuthorRepository;
import com.edu.icesi.LibraryManagement.service.IAuthorService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class AuthorServiceImpl implements IAuthorService {

    private IAuthorRepository authorRepository;

    public AuthorServiceImpl(IAuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    };
    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Boolean uploadAuthor(Long id, Author author) {
        boolean flag = false;
        Optional<Author> oldAuthor = findById(id);
        if(oldAuthor.isPresent()){
            flag=true;
            saveAuthor(author);
        }else{
            throw new NoSuchElementException("No author found with the given ID: " + id);
        }
        return flag;
    }

    @Override
    public Boolean deleteAuthor(Long id) {
        boolean flag = false;
        Optional<Author> author = findById(id);
        if(author.isPresent()){
            authorRepository.delete(author.get());
            flag = true;
        }else{
            throw new NoSuchElementException("No author found with the given ID: " + id);
        }
        return flag;
    }
    @Override
    public List<Book> booksByAuthor(Long id) {
        return authorRepository.findById(id).get().getBooks();
    }
}
