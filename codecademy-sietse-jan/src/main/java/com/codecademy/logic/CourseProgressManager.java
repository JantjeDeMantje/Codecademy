package com.codecademy.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.codecademy.domain.Course;
import com.codecademy.domain.Student;

public class CourseProgressManager {

    private RegistrationManager registrationManager;
    private StudentManager studentManager;
    private Course selectedCourse;

    public CourseProgressManager(Course course) {
        registrationManager = new RegistrationManager();
        studentManager = new StudentManager();
        selectedCourse = course;
    }

    private ArrayList<Integer> getStudents(){
        return registrationManager.getRegistrationsByCourse(selectedCourse.getCourseName());
    }

    public Student getStudentInfoById(int studentId){
        return studentManager.getStudentInfoById(studentId);
    }

    public Map<Student, Double> getAverageWatchPercentagePerStudent(){
        Map<Student, Double> averageWatchPercentagePerStudent = new HashMap<>();

        for (int student : getStudents()) {
            Student studentInfo = getStudentInfoById(student);
            averageWatchPercentagePerStudent.put(studentInfo, 1.1);
        }

        return averageWatchPercentagePerStudent;
    }
}