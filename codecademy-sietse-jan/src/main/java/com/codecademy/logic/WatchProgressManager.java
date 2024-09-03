package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.domain.Course;
import com.codecademy.domain.Student;
import com.codecademy.domain.WatchPercentage;

public class WatchProgressManager {

    private ArrayList<WatchPercentage> watchPercentages;
    private ArrayList<Student> students;
    private ArrayList<Module> modules;
    private StudentManager studentManager;
    private ModuleManager moduleManager;

    public WatchProgressManager() {
        watchPercentages = new ArrayList<>();
        loadStudentManager();
        loadModuleManager();
        createDummyData();
        getAverageWatchPercentagePerStudent();
    }

    private void createDummyData() {
        watchPercentages.add(new WatchPercentage(students.get(0), courses.get(0), 50.0));
        watchPercentages.add(new WatchPercentage(students.get(1), courses.get(1), 75.0));
        watchPercentages.add(new WatchPercentage(students.get(2), courses.get(2), 0.0));
    }

    private void loadCourseManager() {
        courseManager = new CourseManager();
        this.courses = courseManager.getCourses();
    }

    private void loadStudentManager() {
        studentManager = new StudentManager();
        this.students = studentManager.getStudents();
    }

    public ArrayList<WatchPercentage> getWatchPercentages() {
        return watchPercentages;
    }
    
}
