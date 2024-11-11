package com.bookstore.bookstore_api.controller;

import com.bookstore.bookstore_api.dto.AuthorDTO;
import com.bookstore.bookstore_api.model.Author;
import com.bookstore.bookstore_api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = authorDTO.toEntity();  // Converte AuthorDTO para Author
        Author createdAuthor = authorService.createAuthor(author);  // Salva o Author no banco
        AuthorDTO createdAuthorDTO = AuthorDTO.fromEntity(createdAuthor);  // Converte de volta para AuthorDTO
        return new ResponseEntity<>(createdAuthorDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAuthors() {
        List<Author> authors = authorService.listAuthor();  // Lista os Authors
        List<AuthorDTO> authorDTOs = authors.stream()
                .map(AuthorDTO::fromEntity)  // Converte Author para AuthorDTO
                .collect(Collectors.toList());
        return new ResponseEntity<>(authorDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable UUID id, @RequestBody AuthorDTO authorDTO) {
        Author author = authorDTO.toEntity();  // Converte AuthorDTO para Author
        Optional<Author> updatedAuthor = authorService.updateAuthor(id, author);
        return updatedAuthor
                .map(response -> new ResponseEntity<>(AuthorDTO.fromEntity(response), HttpStatus.OK))  // Retorna AuthorDTO atualizado
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable UUID id) {
        boolean deleted = authorService.deleteAuthor(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
