package br.edu.ufam.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/coffeecake";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
