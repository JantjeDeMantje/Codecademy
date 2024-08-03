package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.domain.Course;
import com.codecademy.domain.Difficulty;
import com.codecademy.domain.Registration;

public class RegistrationManager {
    private ArrayList<Registration> registrations;

    public RegistrationManager() {
        createDummyData();
    }

    private void createDummyData() { // This method creates the temp. dummy data
        registrations = new ArrayList<Registration>();

        registrations.add(new Registration(new Course("Course 3", "Subject 3", "Introduction text 3", Difficulty.Expert), false));
        registrations.add(new Registration(new Course("Course 2", "Subject 2", "Introduction text 2", Difficulty.Gevorderd), false));
        registrations.add(new Registration(new Course("Course 3", "Subject 3", "Introduction text 3", Difficulty.Expert), true));

    private void loadCourseManager(){
        courseManager = new CourseManager();
        this.courses = courseManager.getCourses();
    }

    public ArrayList<Registration> getRegistrations() {
        return registrations;
    }

}