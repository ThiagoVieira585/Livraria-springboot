package com.bookstore.bookstore_api.repository;

import com.bookstore.bookstore_api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
