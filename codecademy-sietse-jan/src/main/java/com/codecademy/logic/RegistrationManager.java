package com.codecademy.logic;

import java.util.ArrayList;
import java.util.Map;

import com.codecademy.dataStorage.RegistrationDAO;
import com.codecademy.domain.Course;
import com.codecademy.domain.Registration;
import com.codecademy.domain.Student;

import javafx.scene.control.TableView;

public class RegistrationManager {
    private CourseManager courseManager;
    private RegistrationDAO registrationDAO;

    private ArrayList<Course> courses = new ArrayList<>();
    
    public RegistrationManager(Student student) {
        registrationDAO = new RegistrationDAO();
        loadCourseManager();
    }

    private void loadCourseManager(){ // This method loads all information needed from the courseManager.
        courseManager = new CourseManager();
        this.courses = courseManager.getCourses();
    }

    public ArrayList<Registration> getRegistrations(int studentId){ // This method sends all the registrations that match with the studentId.{
        return registrationDAO.findRegistrationsByStudentId(studentId);
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