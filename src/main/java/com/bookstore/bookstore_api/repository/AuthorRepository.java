package com.bookstore.bookstore_api.repository;

import com.bookstore.bookstore_api.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
