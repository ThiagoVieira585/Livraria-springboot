package com.bookstore.bookstore_api.service;

import com.bookstore.bookstore_api.dto.AuthorDTO;
import com.bookstore.bookstore_api.model.Author;
import com.bookstore.bookstore_api.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // Listar todos os autores
    public List<Author> listAuthor() {
        return authorRepository.findAll();
    }

    // Criar um novo autor
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Atualizar um autor existente
    public Optional<Author> updateAuthor(UUID id, Author author) {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (existingAuthor.isPresent()) {
            Author updatedAuthor = existingAuthor.get();
            updatedAuthor.setName(author.getName());
            updatedAuthor.setNationality(author.getNationality());
            // Se houver livros, pode ser tratado aqui, se necessário
            return Optional.of(authorRepository.save(updatedAuthor));
        }
        return Optional.empty();
    }

    // Deletar um autor
    public boolean deleteAuthor(UUID id) {
        if (authorRepository.findById(id).isPresent()) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Converter Author para AuthorDTO
    public AuthorDTO toDTO(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setNationality(author.getNationality());
        // Se você quiser incluir apenas os IDs dos livros, pode fazer assim:
        dto.setBooks(author.getBooks().stream()
                .map(book -> book.getId()) // Inclui apenas os IDs dos livros
                .collect(Collectors.toList()));
        return dto;
    }

    // Converter AuthorDTO para Author
    public Author fromDTO(AuthorDTO dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setName(dto.getName());
        author.setNationality(dto.getNationality());
        // Os livros não são tratados diretamente aqui, mas podem ser se necessário
        return author;
    }
}
