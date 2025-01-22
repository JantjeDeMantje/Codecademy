package com.codecademy.dataStorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.PreparedStatement;

import com.codecademy.domain.Student;

public class StudentDAO{

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT StudentId, EmailAddress, Name, Birthday, Gender, Address, Zipcode, City, Country\r\n" + //
                                        "FROM Student\r\n" + //
                                        "JOIN Address ON Student.AddressId = Address.AddressId";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    int studentId = resultSet.getInt("StudentId");
                    String name = resultSet.getString("Name");
                    String email = resultSet.getString("EmailAddress");
                    Date birthdate = resultSet.getDate("Birthday");
                    String gender = resultSet.getString("Gender");
                    String address = resultSet.getString("Address");
                    String zipcode = resultSet.getString("Zipcode");	
                    String city = resultSet.getString("City");	
                    String country = resultSet.getString("Country");
                    students.add(new Student(studentId, name, email, birthdate, gender, address, zipcode, city, country));
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
        return students;
    }

    public void createStudent(String name, String email, Date birthdate, String gender, String address, String zipcode, String city, String country) {
        String insertAddressSQL = "INSERT INTO Address (Zipcode, Address, City, Country) VALUES (?, ?, ?, ?)";
        String insertStudentSQL = "INSERT INTO Student (Name, EmailAddress, Birthday, Gender, AddressId) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Insert into Address table
            try (PreparedStatement pstmtAddress = conn.prepareStatement(insertAddressSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmtAddress.setString(1, zipcode);
                pstmtAddress.setString(2, address);
                pstmtAddress.setString(3, city);
                pstmtAddress.setString(4, country);
                pstmtAddress.executeUpdate();
                
                // Get the generated addressID
                ResultSet rs = pstmtAddress.getGeneratedKeys();
                if (rs.next()) {
                    int addressID = rs.getInt(1);
                    
                    // Insert into Student table
                    try (PreparedStatement pstmtStudent = conn.prepareStatement(insertStudentSQL)) {
                        pstmtStudent.setString(1, name);
                        pstmtStudent.setString(2, email);
                        pstmtStudent.setDate(3, birthdate);
                        pstmtStudent.setString(4, gender);
                        pstmtStudent.setInt(5, addressID);
                        pstmtStudent.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }
}