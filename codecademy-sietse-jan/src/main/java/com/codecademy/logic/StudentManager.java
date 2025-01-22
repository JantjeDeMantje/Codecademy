package com.codecademy.logic;

import com.codecademy.dataStorage.StudentDAO;
import com.codecademy.domain.Student;

import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StudentManager {
    private StudentDAO studentDAO;

    public StudentManager() {
        studentDAO = new StudentDAO();
    }

    // This method returns the students arraylist
    public ArrayList<Student> getStudents() {
        return studentDAO.getAllStudents();
    }

    // This method creates a new student object
    public Student createStudent(String name, String email, String birthdate, String gender, String address, String zipcode,
            String city, String country, TableView<Student> studentTable) {

        Student newStudent = new Student(name, email, birthdate, gender, address, zipcode, city, country);

        studentTable.getItems().add(newStudent); // Adds the new student to the table

        studentDAO.createStudent(name, email, convertStringToDate(birthdate), gender, address, zipcode, city, country); // Creates the student in the database
        return newStudent; // returns the new student for log
    }

    // This method deletes a student object
    public void deleteStudent(Student student, TableView<Student> studentTable) {
        studentTable.getItems().remove(student); // Removes the student from the table

        studentDAO.deleteStudent(student); // Deletes the student from the database
    }

    // This method updates a student object
    public void updateStudent(Student student, String name, String email, String birthdate, String gender, String address,
            String zipcode, String city, String country, TableView<Student> studentTable) {
        student.setName(name);
        student.setEmail(email);
        student.setBirthdate(birthdate);
        student.setGender(gender);
        student.setZipcode(zipcode);
        student.setCity(city);
        student.setCountry(country);
        student.setAddress(address);
        studentTable.refresh(); // Refreshes the table
    }

    private Date convertStringToDate(String date) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date parsedDate = inputFormat.parse(date);
            return new Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}