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
    private WatchProgressManager watchProgressManager;
    private Course selectedCourse;
    private ArrayList<Integer> contentItemIds;

    public CourseProgressManager(Course course) {
        registrationManager = new RegistrationManager();
        contentItemManager = new ContentItemManager();
        studentManager = new StudentManager();
        watchProgressManager = new WatchProgressManager();
        selectedCourse = course;

        contentItemIds = getContentItemIds();
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
            averageWatchPercentagePerStudent.put(studentInfo, watchProgressManager.getAverageWatchPercentageByStudent(student, contentItemIds));
        }

        return averageWatchPercentagePerStudent;
    }


    private ArrayList<Integer> getContentItemIds() {
        return contentItemManager.getContentItemIdsByCourse(selectedCourse.getCourseName());
    }

    private Map<Integer, String> getContentItemTitlesMap(ArrayList<Integer> contentItemIds) {
        return contentItemManager.getContentItemTitlesMap(contentItemIds);
    }

    public Map<String, Double> getAverageWatchPercentagePerContentItem() {
        Map<String, Double> averageWatchPercentagePerContentItem = new HashMap<>();

        Map<Integer, String> contentItemTitlesMap = getContentItemTitlesMap(contentItemIds);

        for (int contentItemId : contentItemIds) {
            String contentItemTitle = contentItemTitlesMap.get(contentItemId);
            double averagePercentage = watchProgressManager.getAverageWatchPercentageByContentItem(contentItemId);
            averageWatchPercentagePerContentItem.put(contentItemTitle, averagePercentage);
        }

        return averageWatchPercentagePerContentItem;
    }
}