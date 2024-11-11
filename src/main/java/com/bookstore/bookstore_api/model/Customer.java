package com.bookstore.bookstore_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.bookstore.bookstore_api.model.Credential;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @Embedded
    private Credential credential;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zipCode;

}
