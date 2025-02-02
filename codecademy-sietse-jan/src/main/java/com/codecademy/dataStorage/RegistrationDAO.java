package com.codecademy.dataStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.codecademy.domain.Registration;

public class RegistrationDAO {
    // This query is used to find all registrations for a student by studentId
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

    // This query is used to create a registration for a student
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

    // This query is used to delete a registration for a student
    public void deleteRegistration(int studentId, String courseName) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                String query = "DELETE FROM Registration WHERE StudentId = ? AND CourseName = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, studentId);
                preparedStatement.setString(2, courseName);
                preparedStatement.executeUpdate();
                System.out.println("Registration removed: " + courseName);
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

    // This query is used to find all registrations for a course by courseName
    public ArrayList<Integer> findRegistrationsByCourse(String courseName) {
        System.out.println("RegistrationDAO.findRegistrationsByCourse() called: " + courseName);
        ArrayList<Integer> studentIds = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                String query = "SELECT StudentId FROM Registration WHERE CourseName = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, courseName);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int studentId = resultSet.getInt("StudentId");
                    studentIds.add(studentId);
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
        System.out.println("Returning studentIds: " + studentIds);
        return studentIds;
    }
}