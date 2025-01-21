package com.codecademy.logic;

import com.codecademy.dataStorage.StudentDAO;
import com.codecademy.domain.Student;

import javafx.scene.control.TableView;

import java.util.ArrayList;

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

        // Student newStudent = new Student(name, email, birthdate, gender, zipcode, city, country);

        // studentTable.getItems().add(newStudent); // Adds the new student to the table
        return null;
    }

    // This method deletes a student object
    public void deleteStudent(Student student, TableView<Student> studentTable) {
        studentTable.getItems().remove(student); // Removes the student from the table
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
}
