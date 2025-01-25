package com.codecademy.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.codecademy.domain.Course;
import com.codecademy.domain.Student;

public class CourseProgressManager {

    private RegistrationManager registrationManager;
    private ContentItemManager contentItemManager;
    private StudentManager studentManager;
    private Course selectedCourse;

    public CourseProgressManager(Course course) {
        registrationManager = new RegistrationManager();
        contentItemManager = new ContentItemManager();
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

    private ArrayList<Integer> getContentItemIds(){
        return contentItemManager.getContentItemIdsByCourse(selectedCourse.getCourseName());
    }

    public Map<String, Double> getAverageWatchPercentagePerContentItem(){
        Map<String, Double> averageWatchPercentagePerContentItem = new HashMap<>();

        for (String contentItemTitle : getContentItemTitles()) {
            averageWatchPercentagePerContentItem.put(contentItemTitle, 1.1);
        }

        return averageWatchPercentagePerContentItem;
    }

    public ArrayList<String> getContentItemTitles(){
        return contentItemManager.getContentItemTitles(getContentItemIds());
    }
}