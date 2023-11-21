package com.edu.icesi.LibraryManagement.service.impl;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.edu.icesi.LibraryManagement.persistence.model.Author;
import com.edu.icesi.LibraryManagement.persistence.repository.IAuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AuthorServiceImplTest {

    @Mock
    private IAuthorRepository authorRepositoryMock;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllAuthors() {
        Author author1 = new Author();
        author1.setId(1L);
        author1.setName("John Doe");
        author1.setNationality("American");


        Author author2 = new Author();
        author2.setId(2L);
        author2.setName("Jane Smith");
        author2.setNationality("British");


        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);
        when(authorRepositoryMock.findAll()).thenReturn(authors);

        List<Author> result = authorService.getAllAuthors();
        Assertions.assertEquals(authors.size(), result.size());
        Assertions.assertEquals(authors, result); //Verifican que el tamaño de las listas sean iguales

    }

    @Test
    public void testFindById() {
        Long authorId = 10L;
        Author author = new Author();
        author.setId(authorId);
        author.setName("Diana");
        author.setNationality("Colombian");


        when(authorRepositoryMock.findById(authorId)).thenReturn(Optional.of(author));

        Optional<Author> result = authorService.findById(authorId);
        Assertions.assertTrue(result.isPresent());

        Author foundAuthor = result.get();
        Assertions.assertEquals(author.getId(), foundAuthor.getId());
        Assertions.assertEquals(author.getName(), foundAuthor.getName());
        Assertions.assertEquals(author.getNationality(), foundAuthor.getNationality());

    }

    @Test
    public void testSaveAuthor() {
        Author author = new Author(); // Inicializar un autor
        author.setId(1L);
        author.setName("Lorena");
        author.setNationality("Colombian");

        when(authorRepositoryMock.save(author)).thenReturn(author);

        Author savedAuthor = authorService.saveAuthor(author);
        Assertions.assertEquals(author, savedAuthor);
    }

    @Test
    public void testUploadAuthor_existingAuthor() {
        Long authorId = 1L;
        Author existingAuthor = new Author();
        existingAuthor.setId(authorId);
        existingAuthor.setName("Carlos");
        existingAuthor.setNationality("Colombia");


        when(authorRepositoryMock.findById(authorId)).thenReturn(Optional.of(existingAuthor));

        Author updatedAuthor = new Author();
        updatedAuthor.setId(authorId);
        updatedAuthor.setName("Javier");
        updatedAuthor.setNationality("Colombian");


        when(authorRepositoryMock.save(updatedAuthor)).thenReturn(updatedAuthor);

        Boolean result = authorService.uploadAuthor(authorId, updatedAuthor);
        Assertions.assertTrue(result);
    }

    @Test
    public void testUploadAuthor_nonExistingAuthor() {
        Long authorId = 1L;

        when(authorRepositoryMock.findById(authorId)).thenReturn(Optional.empty());

        Author newAuthor = new Author();
        newAuthor.setId(authorId);
        newAuthor.setName("Gabriel Garcia Marquez");
        newAuthor.setNationality("Colombian");


        Boolean result = authorService.uploadAuthor(authorId, newAuthor);
        Assertions.assertFalse(result);
    }

    @Test
    public void testDeleteAuthor_existingAuthor() {
        Long authorId = 1L;
        Author existingAuthor = new Author();
        existingAuthor.setId(authorId);
        existingAuthor.setName("Jorge Luis Borges");
        existingAuthor.setNationality("Argentinian");
        // Establecer otras propiedades...

        when(authorRepositoryMock.findById(authorId)).thenReturn(Optional.of(existingAuthor));

        Boolean result = authorService.deleteAuthor(authorId);
        Assertions.assertTrue(result);
    }

    @Test
    public void testDeleteAuthor_nonExistingAuthor() {
        Long authorId = 1L;

        when(authorRepositoryMock.findById(authorId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            authorService.deleteAuthor(authorId);
        });
    }
}