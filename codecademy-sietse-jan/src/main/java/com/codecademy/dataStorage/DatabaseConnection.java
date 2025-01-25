package com.codecademy.dataStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection { // This class makes a connection with our database.
    private static final String URL = "jdbc:sqlserver://localhost;databaseName=CodeCademyDB;user=DB_Admin;password=Codecademy;"; // The URL to connect to the database

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL); // Establish a connection with the database
            return connection;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}

class DatabaseConnectionTest { // A test class to check if the connection is successful
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Connection successfull!");
        } else {
            System.out.println("Connection failed!");
        }
    }
}