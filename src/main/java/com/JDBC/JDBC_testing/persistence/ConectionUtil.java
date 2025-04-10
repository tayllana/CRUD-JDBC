package com.JDBC.JDBC_testing.persistence;

import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// classe usada para criar a conexão com o banco de dados
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ConectionUtil {
    public static Connection getConection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3307/JDBCTEST", "root", "mysql");
    }
}
