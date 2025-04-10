package com.JDBC.JDBC_testing.persistence.entity;

import lombok.Data;

@Data
public class UserEntity {
// Classe que representa a tabela de usuários no banco de dados
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;


}
