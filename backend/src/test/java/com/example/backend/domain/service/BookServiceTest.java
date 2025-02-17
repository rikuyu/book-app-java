package com.example.backend.domain.service;

import com.example.backend.domain.entity.Book;
import com.example.backend.domain.entity.Status;
import com.example.backend.infra.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService service;

    @Mock
    private BookMapper mapper;

    Book mockBook1 = new Book(1, "book1", Status.AVAILABLE);
    Book mockBook2 = new Book(2, "book2", Status.AVAILABLE);
    List<Book> mockBooks = Arrays.asList(mockBook1, mockBook2);

    @Test
    void findAllBooks_success() {
        when(mapper.findAllBooks()).thenReturn(mockBooks);
        List<Book> books = service.findAllBooks();

        assertEquals(mockBooks.size(), books.size());
        assertEquals(mockBooks.get(0).title(), books.get(0).title());

        verify(mapper, times(1)).findAllBooks();
    }

    @Test
    void insertBook_success() {
        doNothing().when(mapper).insertBook(mockBook1);
        service.insertBook(mockBook1);
        verify(mapper, times(1)).insertBook(mockBook1);
    }

    @Test
    void findById_success() {
        when(mapper.findById(1)).thenReturn(mockBook1);
        var book = service.findById(1);

        assertEquals(mockBook1.title(), book.title());

        verify(mapper, times(1)).findById(1);
    }

    @Test
    void deleteById_success() {
        when(mapper.deleteById(1)).thenReturn(1);
        int affectedRows = service.deleteById(1);
        assertEquals(1, affectedRows);
        verify(mapper, times(1)).deleteById(1);
    }

    @Test
    void search_success() {
        var keyword = "ok1";
        when(mapper.search(keyword)).thenReturn(Arrays.asList(mockBook1));
        var books = service.search(keyword);

        assertEquals(mockBook1.title(), books.get(0).title());
        verify(mapper, times(1)).search(keyword);
    }

    @Test
    void getPopularBooks_success() {
        when(mapper.getPopularBooks()).thenReturn(mockBooks);
        var popularBooks = service.getPopularBooks();

        assertEquals(mockBooks.size(), popularBooks.size());
        verify(mapper, times(1)).getPopularBooks();
    }
}