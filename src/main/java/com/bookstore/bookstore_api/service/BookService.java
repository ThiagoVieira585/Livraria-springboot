package com.bookstore.bookstore_api.service;

import com.bookstore.bookstore_api.model.Book;
import com.bookstore.bookstore_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public Optional<Book> updateBook(UUID id, Book book) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(book.getTitle());
            existingBook.setDescription(book.getDescription());
            existingBook.setPrice(book.getPrice());
            existingBook.setAuthor(book.getAuthor());
            return bookRepository.save(existingBook);
        });
    }

    public boolean deleteBook(UUID id){
        if (bookRepository.findById(id).isPresent()){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
