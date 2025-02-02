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

    // This query selects all students from the Student table and joins them with the Address table to get the address information.
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT StudentId, EmailAddress, Name, Birthday, Gender, Address, Zipcode, City, Country, Student.AddressId\r\n" + //
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
                    int addressId = resultSet.getInt("AddressId");
                    students.add(new Student(studentId, name, email, birthdate, gender, address, zipcode, city, country, addressId));
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

    // This query inserts a new student into the Student table and the corresponding address into the Address table.
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

    // This query deletes a student from the Student table and the corresponding address from the Address table.
    public void deleteStudent (Student student) {
        String deleteStudentSQL = "DELETE FROM Student WHERE StudentId = ?";
        String deleteAddressSQL = "DELETE FROM Address WHERE AddressId = ?";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement pstmtStudent = conn.prepareStatement(deleteStudentSQL)) {
                pstmtStudent.setInt(1, student.getStudentId());
                pstmtStudent.executeUpdate();
                
                try (PreparedStatement pstmtAddress = conn.prepareStatement(deleteAddressSQL)) {
                    pstmtAddress.setInt(1, student.getAddressId());
                    pstmtAddress.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This query updates a student in the Student table and the corresponding address in the Address table.
    public void updateStudent(Student student, String name, String email, Date birthdate, String gender, String address, String zipcode, String city, String country) {
        String updateAddressSQL = "UPDATE Address SET Zipcode = ?, Address = ?, City = ?, Country = ? WHERE AddressId = ?";
        String updateStudentSQL = "UPDATE Student SET Name = ?, EmailAddress = ?, Birthday = ?, Gender = ? WHERE StudentId = ?";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Update Address table
            try (PreparedStatement pstmtAddress = conn.prepareStatement(updateAddressSQL)) {
                pstmtAddress.setString(1, zipcode);
                pstmtAddress.setString(2, address);
                pstmtAddress.setString(3, city);
                pstmtAddress.setString(4, country);
                pstmtAddress.setInt(5, student.getAddressId());
                pstmtAddress.executeUpdate();
            }
            
            // Update Student table
            try (PreparedStatement pstmtStudent = conn.prepareStatement(updateStudentSQL)) {
                pstmtStudent.setString(1, name);
                pstmtStudent.setString(2, email);
                pstmtStudent.setDate(3, birthdate);
                pstmtStudent.setString(4, gender);
                pstmtStudent.setInt(5, student.getStudentId());
                pstmtStudent.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // This query checks if an email address is unique in the Student table.
    public boolean checkUniqueEmail(String email, int studentId) {
        String query = "SELECT EmailAddress FROM Student WHERE EmailAddress = ? AND StudentId != ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, email);
                pstmt.setInt(2, studentId);
                ResultSet rs = pstmt.executeQuery();
                return !rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // This query gets a student by their studentId from the Student table and joins them with the Address table to get the address information.
    public Student getStudentInfoById(int studentId) {
        Student student = null;
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                String query = "SELECT StudentId, EmailAddress, Name, Birthday, Gender, Address, Zipcode, City, Country, Student.AddressId " +
                               "FROM Student " +
                               "JOIN Address ON Student.AddressId = Address.AddressId " +
                               "WHERE StudentId = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, studentId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String email = resultSet.getString("EmailAddress");
                    Date birthdate = resultSet.getDate("Birthday");
                    String gender = resultSet.getString("Gender");
                    String address = resultSet.getString("Address");
                    String zipcode = resultSet.getString("Zipcode");
                    String city = resultSet.getString("City");
                    String country = resultSet.getString("Country");
                    int addressId = resultSet.getInt("AddressId");
                    student = new Student(studentId, name, email, birthdate, gender, address, zipcode, city, country, addressId);
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
        return student;
    }
}