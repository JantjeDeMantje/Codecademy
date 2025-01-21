package com.codecademy.dataStorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

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

    public void createStudent(String name, String email, String birthdate, String gender, String zipcode, String city, String country){

    }
}