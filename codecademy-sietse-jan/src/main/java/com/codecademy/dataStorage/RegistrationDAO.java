package com.codecademy.dataStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.codecademy.domain.Registration;

public class RegistrationDAO {
    public ArrayList<Registration> findRegistrationsByStudentId(int studentId) {
        ArrayList<Registration> registrations = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                String query = "SELECT CourseName, RegistrationDate FROM Registration WHERE StudentId = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, studentId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String courseName = resultSet.getString("CourseName");
                    String registrationDate = resultSet.getString("RegistrationDate");
                    registrations.add(new Registration(studentId, courseName, registrationDate));
                    System.out.println("Registration found: " + courseName);
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return registrations;
    }

    public void createRegistration(int studentId, String courseName) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                String query = "INSERT INTO Registration (StudentId, CourseName) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, studentId);
                preparedStatement.setString(2, courseName);
                preparedStatement.executeUpdate();
                System.out.println("Registration created: " + courseName);
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
}
