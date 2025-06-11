package com.rafael.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    public static Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/gerenciador_financeiro";
        String user = "root";
        String password = "1997";
        return DriverManager.getConnection(url, user, password);
    }
}
