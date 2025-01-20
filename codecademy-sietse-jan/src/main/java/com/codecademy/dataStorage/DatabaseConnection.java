package com.codecademy.dataStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://localhost;databaseName=CodeCademyDB;user=DB_Admin;password=Codecademy;";
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL);
            return connection;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}

class DatabaseConnectionTest {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Connection successfull!");
        } else {
            System.out.println("Connection failed!");
        }
    }
}