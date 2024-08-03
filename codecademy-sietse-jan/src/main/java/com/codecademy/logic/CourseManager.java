package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.domain.Course;
import com.codecademy.domain.Difficulty;

public class CourseManager {
    private ArrayList<Course> courses;

    public CourseManager() {
        createDummyData();
    }

    private void createDummyData() { // This method creates the temp. dummy data
        courses = new ArrayList<Course>();

        courses.add(new Course("Webdesign", "HTML uitleg", "Wauw wat is HTML toch lastig, maar of nee toch niet", Difficulty.Beginner));
        courses.add(new Course("Java", "JavaFX gebruik", "Java is al lastig, maar nu kan je ook met JavaFX iets maken!", Difficulty.Gevorderd));
        courses.add(new Course("Webdesign", "JavaScript gebruik", "Naast HTML en CSS, kan je ook gebruik maken van JavaScript!", Difficulty.Expert));
        courses.add(new Course("Database", "SQL manager", "Wat zijn queries en hoe maak je ze? Leer dat in deze cursus!", Difficulty.Beginner));
        courses.add(new Course("Binair", "Binair puzzelen", "Is sudoku te makkelijk voor jou? Probeer dan nu de nieuwe puzzel Binair!", Difficulty.Gevorderd));
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}