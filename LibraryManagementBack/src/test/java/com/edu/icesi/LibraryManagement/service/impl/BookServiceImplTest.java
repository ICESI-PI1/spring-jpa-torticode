package com.edu.icesi.LibraryManagement.service.impl;

import com.edu.icesi.LibraryManagement.persistence.model.Author;
import com.edu.icesi.LibraryManagement.persistence.model.Book;
import com.edu.icesi.LibraryManagement.persistence.repository.IBookRepository;
import com.edu.icesi.LibraryManagement.service.dto.AuthorBookDTO;
import com.edu.icesi.LibraryManagement.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    private BookServiceImpl bookService;
    private IBookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = mock(IBookRepository.class);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    void getAllBooks() {
        Book book1 = new Book();
        book1.setTitle("The Great Gatsby");
        Book book2 = new Book();
        book2.setTitle("To Kill a Mockingbird");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> result = bookService.getAllBooks();
        assertEquals(2, result.size());
    }

    @Test
    void findById_existingBook() {
        Long id = 1L;
        Book book = new Book();
        book.setId(id);
        book.setTitle("The Catcher in the Rye");

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.findById(id);
        assertTrue(result.isPresent());
        assertEquals("The Catcher in the Rye", result.get().getTitle());
    }

    @Test
    void findById_nonExistingBook() {
        Long id = 10L;
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Book> result = bookService.findById(id);
        assertTrue(result.isEmpty());
    }

    @Test
    void saveBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("1984");

        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.saveBook(book);
        assertEquals(book.getTitle(), savedBook.getTitle());
    }

    @Test
    void uploadBook_existingBook() {
        Long id = 1L;
        Book existingBook = new Book();
        existingBook.setId(id);
        existingBook.setTitle("The Hobbit");

        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));

        Book updatedBook = new Book();
        updatedBook.setId(id);
        updatedBook.setTitle("The Lord of the Rings");

        Boolean result = bookService.uploadBook(id, updatedBook);
        assertTrue(result);
    }



    @Test
    void deleteBook_existingBook() {
        Long id = 1L;
        Book existingBook = new Book();
        existingBook.setId(id);
        existingBook.setTitle("The Shining");

        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBook));

        Boolean result = bookService.deleteBook(id);
        assertTrue(result);
    }

    @Test
    void deleteBook_nonExistingBook() {
        Long id = 1L;
        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> bookService.deleteBook(id));
    }

    @Test
    void getBooksByAuthor() {
        Long authorId = 1L;

        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("1984");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Animal Farm");

        Author author = new Author();
        author.setId(authorId);
        author.setName("George Orwell");

        book1.setAuthor(author);
        book2.setAuthor(author);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<AuthorBookDTO> result = bookService.getBooksbyAuthor(authorId);
        assertEquals(2, result.size());

        AuthorBookDTO dto1 = result.get(0);
        assertEquals("George Orwell", dto1.getAuthorName());
        assertEquals("1984", dto1.getBookTitle());

        AuthorBookDTO dto2 = result.get(1);
        assertEquals("George Orwell", dto2.getAuthorName());
        assertEquals("Animal Farm", dto2.getBookTitle());
    }
}
