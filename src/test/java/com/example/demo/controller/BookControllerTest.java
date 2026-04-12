package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.DTO.ApiResponse;
import com.example.demo.DTO.BookDTO;
import com.example.demo.services.BookService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    // GET /api/books/listBook
    @Test
    void getBooks_shouldReturnBooks() throws Exception {
        BookDTO book = new BookDTO();
        book.setTitle("Test Book");

        when(bookService.getAllBooks())
                .thenReturn(List.of(book));

        mockMvc.perform(get("/api/books/listBook"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Book"));
    }

    // POST /api/books/createBook
    @Test
    void addBook_shouldCreateBook() throws Exception {
        BookDTO book = new BookDTO();
        book.setTitle("New Book");

        ApiResponse<BookDTO> response =
                new ApiResponse<>("created", book);

        when(bookService.addBook(
                "New Book",
                "Author",
                LocalDate.parse("2023-01-01")))
                .thenReturn(response);

        mockMvc.perform(post("/api/books/createBook")
                        .param("title", "New Book")
                        .param("author", "Author")
                        .param("publishDate", "2023-01-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("created"))
                .andExpect(jsonPath("$.data.title").value("New Book"));
    }

    // POST /api/books/readBook
    @Test
    void readBook_shouldMarkBookAsRead() throws Exception {
        ApiResponse<BookDTO> response =
                new ApiResponse<>("read", new BookDTO());

        when(bookService.readBook(1L, 10L))
                .thenReturn(response);

        mockMvc.perform(post("/api/books/readBook")
                        .param("bookId", "1")
                        .param("userId", "10"))
                .andExpect(status().isContinue())
                .andExpect(jsonPath("$.message").value("read"));
    }

    // POST /api/books/unReadBook
    @Test
    void unReadBook_shouldMarkBookAsUnread() throws Exception {
        ApiResponse<BookDTO> response =
                new ApiResponse<>("unread", new BookDTO());

        when(bookService.unReadBook(1L))
                .thenReturn(response);

        mockMvc.perform(post("/api/books/unReadBook")
                        .param("bookId", "1"))
                .andExpect(status().isContinue())
                .andExpect(jsonPath("$.message").value("unread"));
    }

    // PATCH /api/books/updateBook
    @Test
    void updateBook_shouldUpdateBook() throws Exception {
        ApiResponse<BookDTO> response =
                new ApiResponse<>("updated", new BookDTO());

        when(bookService.updateBook(
                1L, "Updated", "Author",
                LocalDate.parse("2023-02-01")))
                .thenReturn(response);

        mockMvc.perform(patch("/api/books/updateBook")
                        .param("id", "1")
                        .param("title", "Updated")
                        .param("author", "Author")
                        .param("publishDate", "2023-02-01"))
                .andExpect(status().isContinue())
                .andExpect(jsonPath("$.message").value("updated"));
    }

    // DELETE /api/books/delBook
    @Test
    void delBook_shouldDeleteBook() throws Exception {
        ApiResponse<BookDTO> response =
                new ApiResponse<>("deleted", null);

        when(bookService.valDelBook(1L))
                .thenReturn(response);

        mockMvc.perform(delete("/api/books/delBook")
                        .param("id", "1"))
                .andExpect(status().isContinue())
                .andExpect(jsonPath("$.message").value("deleted"));
    }
}
