package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.dataStorage.CourseDAO;
import com.codecademy.domain.Course;

public class CourseManager {
    private CourseDAO courseDAO;

    public CourseManager() {
        courseDAO = new CourseDAO();
    }

        // This method returns the students arraylist
    public ArrayList<Course> getCourses() {
        return courseDAO.getAllCourses();
    }
}