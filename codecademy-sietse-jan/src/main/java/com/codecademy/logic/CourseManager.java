package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.dataStorage.CourseDAO;
import com.codecademy.domain.Course;

// This class is the manager of the courses. It is responsible for the communication between the DAO and other classes.
public class CourseManager {
    private CourseDAO courseDAO;

    public CourseManager() {
        courseDAO = new CourseDAO();
    }

    public ArrayList<Course> getCourses() { // This method returns the students arraylist
        return courseDAO.getAllCourses();
    }

    public Course getCourseInfo(String courseName) { // This method sends all the course information that match with the courseName.
        return courseDAO.getCourseInfo(courseName);
    }
}