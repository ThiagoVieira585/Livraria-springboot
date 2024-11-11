package com.bookstore.bookstore_api.dto;

import com.bookstore.bookstore_api.model.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    private UUID id;
    private String name;
    private String nationality;
    private List<UUID> books;  // Lista de IDs de livros

    public static AuthorDTO fromEntity(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setNationality(author.getNationality());
        // Inclui os IDs dos livros
        dto.setBooks(author.getBooks().stream()
                .map(book -> book.getId())
                .collect(Collectors.toList()));
        return dto;
    }

    public Author toEntity() {
        Author author = new Author(); // Construtor padrão
        author.setId(this.id);             // Usando os setters para atribuir os valores
        author.setName(this.name);
        author.setNationality(this.nationality);
        return author;
    }
}
