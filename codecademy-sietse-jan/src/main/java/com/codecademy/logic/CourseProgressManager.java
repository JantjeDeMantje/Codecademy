package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.domain.Course;

public class CourseProgressManager {

    private RegistrationManager registrationManager;
    private Course selectedCourse;

    public CourseProgressManager(Course course) {
        registrationManager = new RegistrationManager();
        selectedCourse = course;
    }

    public ArrayList<Integer> getStudents(){
        System.out.println("CourseProgressManager.getStudents() called " + selectedCourse.getCourseName());
        return registrationManager.getRegistrationsByCourse(selectedCourse.getCourseName());
    }
}