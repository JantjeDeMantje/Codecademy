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

        courses.add(new Course("Course 1", "Subject 1", "Introduction text 1", Difficulty.Beginner));
        courses.add(new Course("Course 2", "Subject 2", "Introduction text 2", Difficulty.Gevorderd));
        courses.add(new Course("Course 3", "Subject 3", "Introduction text 3", Difficulty.Expert));
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}