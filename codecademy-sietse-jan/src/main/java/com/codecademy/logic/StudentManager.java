package com.codecademy.logic;

import com.codecademy.dataStorage.StudentDAO;
import com.codecademy.domain.Student;

import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


// This class is the manager of the Students. It is responsible for the communication between the DAO and other classes.
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

        studentDAO.createStudent(name, email, convertStringToDate(birthdate), gender, address, zipcode, city, country); // Creates the student in the database

        // Retrieve the newly created student from the database
        ArrayList<Student> students = studentDAO.getAllStudents();
        Student newStudent = students.get(students.size() - 1);

        studentTable.getItems().add(newStudent); // Adds the new student to the table

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

        studentDAO.updateStudent(student, name, email,convertStringToDate(birthdate), gender, address, zipcode, city, country); // Updates the student in the database

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

    // This method checks if the email is unique
    public boolean checkUniqueEmail(String email, int studentId) {
        return studentDAO.checkUniqueEmail(email, studentId);
    }


    // This method converts a string to a date
    @SuppressWarnings("exports")
    public Date convertStringToDate(String date) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date parsedDate = inputFormat.parse(date);
            return new Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    // This method sends all the student information that match with the studentId.
    public Student getStudentInfoById(int studentId) {
        return studentDAO.getStudentInfoById(studentId);
    }

}