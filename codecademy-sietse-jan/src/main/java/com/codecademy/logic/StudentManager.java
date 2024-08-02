package com.codecademy.logic;

import com.codecademy.domain.Student;
import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students;

    public StudentManager() {
        createDummyData();
    }

    private void createDummyData() { // This method creates the temp. dummy data
        students = new ArrayList<Student>();

        students.add(new Student("Sietse 't Hooft", "sietse@gmail.com", "01-01-2004", "Man", "1234AB", "Breda", "Nederland"));
        students.add(new Student("Jan Roelofs", "jan@gmail.com", "01-01-2004", "Man", "4321AB", "Breda", "Nederland"));
        students.add(new Student("Dikke de Lul", "dikke@gmail.com", "01-01-2004", "Man", "3412AB", "Breda", "Nederland"));

    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}