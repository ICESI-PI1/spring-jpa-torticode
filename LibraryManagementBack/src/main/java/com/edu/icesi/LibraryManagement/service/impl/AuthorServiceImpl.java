package com.edu.icesi.LibraryManagement.service.impl;

import com.edu.icesi.LibraryManagement.persistence.model.Author;
import com.edu.icesi.LibraryManagement.persistence.repository.IAuthorRepository;
import com.edu.icesi.LibraryManagement.service.IAuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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

        Optional<Author> oldAuthorOptional = findById(id);
        if(oldAuthorOptional.isPresent()){
            Author oldAuthor = oldAuthorOptional.get();
            oldAuthor.setName(author.getName());
            oldAuthor.setNationality(author.getNationality());
            saveAuthor(author);
            return true;
        }else{
            return false;

        }

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

}
