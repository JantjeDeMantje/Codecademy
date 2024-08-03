package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.dataStorage.DataHolder;
import com.codecademy.domain.Course;
import com.codecademy.domain.Registration;

public class RegistrationManager {
    private CourseManager courseManager;

    private ArrayList<Registration> registrations = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    
    public RegistrationManager() {
        loadCourseManager();
        createDummyData();
    }

    private void createDummyData() { // This method creates the temp. dummy data
        registrations.add(new Registration(DataHolder.getInstance().getSelectedStudent(), courses.get(1), "20-03-2024"));
        registrations.add(new Registration(DataHolder.getInstance().getSelectedStudent(), courses.get(3), "24-05-2024"));
        registrations.add(new Registration(DataHolder.getInstance().getSelectedStudent(), courses.get(4), "01-08-2024"));
    }

    private void loadCourseManager(){
        courseManager = new CourseManager();
        this.courses = courseManager.getCourses();
    }

    public ArrayList<Registration> getRegistrations() {
        return registrations;
    }

    public ArrayList<Course> getCourses(){
        return courses;
    }

}