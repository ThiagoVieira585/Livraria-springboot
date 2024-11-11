package com.bookstore.bookstore_api.controller;

import com.bookstore.bookstore_api.model.Author;
import com.bookstore.bookstore_api.model.Book;
import com.bookstore.bookstore_api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/books")
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createBook = bookService.createBook(book);
        return ResponseEntity.ok().body(createBook);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Book> updateBook(@PathVariable UUID id, @RequestBody Book book) {
        Optional<Book> updateBook = bookService.updateBook(id,book);
        return updateBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable UUID id) {
        boolean deleteBook = bookService.deleteBook(id);
        if (deleteBook) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
