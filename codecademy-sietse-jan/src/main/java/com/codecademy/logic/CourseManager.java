package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.dataStorage.CourseDAO;
import com.codecademy.domain.Course;
import com.codecademy.domain.Difficulty;
import com.codecademy.domain.Student;

public class CourseManager {
    private CourseDAO courseDAO;

    public CourseManager() {
        courseDAO = new CourseDAO();
    }

        // This method returns the students arraylist
    public ArrayList<Course> getCourses() {
        return courseDAO.getAllCourses();
    }

    // private void createDummyData() { // This method creates the temp. dummy data
    //     courses = new ArrayList<Course>();

    //     courses.add(new Course("Webdesign 1", "HTML uitleg", "Wauw wat is HTML toch lastig, maar of nee toch niet", Difficulty.Beginner));
    //     courses.add(new Course("Java 1", "JavaFX gebruik", "Java is al lastig, maar nu kan je ook met JavaFX iets maken!", Difficulty.Gevorderd));
    //     courses.add(new Course("Webdesign 2", "JavaScript gebruik", "Naast HTML en CSS, kan je ook gebruik maken van JavaScript!", Difficulty.Expert));
    //     courses.add(new Course("Database 1", "SQL manager", "Wat zijn queries en hoe maak je ze? Leer dat in deze cursus!", Difficulty.Beginner));
    //     courses.add(new Course("Binair 1", "Binair puzzelen", "Is sudoku te makkelijk voor jou? Probeer dan nu de nieuwe puzzel Binair!", Difficulty.Gevorderd));
    // }

}