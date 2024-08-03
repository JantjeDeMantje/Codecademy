package com.codecademy.logic;

import com.codecademy.domain.Student;

import javafx.scene.control.TableView;

import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students;

    public StudentManager() {
        createDummyData(); // Calls the createDummyData method when started.
    }

    private void createDummyData() { // This method creates the temp. dummy data
        students = new ArrayList<Student>();

        students.add(new Student("Sietse 't Hooft", "sietse@gmail.com", "01-01-2004", "Man", "1234AB", "Breda",
                "Nederland"));
        students.add(new Student("Jan Roelofs", "jan@gmail.com", "01-01-2004", "Man", "4321AB", "Breda", "Nederland"));
        students.add(
                new Student("Gert van Dijk", "gert@gmail.com", "01-01-2004", "Man", "3412AB", "Breda", "Nederland"));

    }

    // This method returns the students arraylist
    public ArrayList<Student> getStudents() {
        return students;
    }

    // This method creates a new student object
    public Student createStudent(String name, String email, String birthdate, String gender, String zipcode,
            String city, String country, TableView<Student> studentTable) {

        Student newStudent = new Student(name, email, birthdate, gender, zipcode, city, country);

        studentTable.getItems().add(newStudent); // Adds the new student to the table
        return newStudent;
    }

    // This method deletes a student object
    public void deleteStudent(Student student, TableView<Student> studentTable) {
        studentTable.getItems().remove(student); // Removes the student from the table
    }

    // This method updates a student object
    public void updateStudent(Student student, String name, String email, String birthdate, String gender,
            String zipcode, String city, String country, TableView<Student> studentTable) {
        student.setName(name);
        student.setEmail(email);
        student.setBirthdate(birthdate);
        student.setGender(gender);
        student.setZipcode(zipcode);
        student.setCity(city);
        student.setCountry(country);
        studentTable.refresh(); // Refreshes the table
    }
}
