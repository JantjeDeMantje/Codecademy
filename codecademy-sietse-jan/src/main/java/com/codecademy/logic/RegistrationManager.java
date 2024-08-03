package com.codecademy.logic;

import java.util.ArrayList;
import java.util.Map;

import com.codecademy.dataStorage.DataHolder;
import com.codecademy.domain.Course;
import com.codecademy.domain.Registration;
import com.codecademy.domain.Student;

import javafx.scene.control.TableView;

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

    private void loadCourseManager(){ // This method loads all information needed from the courseManager.
        courseManager = new CourseManager();
        this.courses = courseManager.getCourses();
    }

    public ArrayList<Registration> getRegistrations() {
        return registrations;
    }

    public ArrayList<Course> getCourses(){
        return courses;
    }

    public Course getCourseInfo(String courseName){ // This method sends all the course information that match with the courseName.
        for (Course course : courses ){
            if (course.getCourseName() == courseName) {
                        return course;
            }
        }
        return null; // No match? --> null
    }

    public void createRegistration(Student student, Course course, TableView<Map.Entry<String,Boolean>> courseRegistrationTable ){
        courseRegistrationTable.refresh();
    }

    public void deleteRegistration(Student student, Course course, TableView<Map.Entry<String,Boolean>> courseRegistrationTable){
        courseRegistrationTable.refresh(); 
    }

}