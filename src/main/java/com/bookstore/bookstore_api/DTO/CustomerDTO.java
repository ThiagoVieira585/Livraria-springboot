package com.bookstore.bookstore_api.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerDTO {
    private UUID id;
    private String name;
    private String email;   // Extraído de Credential
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zipCode;
}
