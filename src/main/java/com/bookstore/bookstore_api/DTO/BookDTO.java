package com.bookstore.bookstore_api.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BookDTO {
    private UUID id;
    private String title;
    private String description;
    private Double price;
    private UUID authorId;  // Exibe apenas o ID do autor
}