package com.codecademy.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.codecademy.domain.Course;
import com.codecademy.domain.Student;

// This class is the manager of the CourseProgress. It is responsible for the communication between all the other managers and controller.
public class CourseProgressManager {

    private RegistrationManager registrationManager;
    private StudentManager studentManager;
    private ContentItemManager contentItemManager;
    private WatchProgressManager watchProgressManager;

    private Course selectedCourse;
    private ArrayList<Integer> contentItemIds;

    public CourseProgressManager(Course course) {
        registrationManager = new RegistrationManager();
        studentManager = new StudentManager();
        contentItemManager = new ContentItemManager();
        watchProgressManager = new WatchProgressManager();
        selectedCourse = course;

        contentItemIds = getContentItemIds();
    }

    // This private method gets all the students that are registered to the selected course.
    private ArrayList<Integer> getStudents(){
        return registrationManager.getRegistrationsByCourse(selectedCourse.getCourseName());
    }

    // This method gets the student information by the studentId.
    public Student getStudentInfoById(int studentId){
        return studentManager.getStudentInfoById(studentId);
    }

    // This method gets the average watch percentage per student.
    public Map<Student, Double> getAverageWatchPercentagePerStudent(){
        Map<Student, Double> averageWatchPercentagePerStudent = new HashMap<>();

        for (int student : getStudents()) {
            Student studentInfo = getStudentInfoById(student);
            averageWatchPercentagePerStudent.put(studentInfo, watchProgressManager.getAverageWatchPercentageByStudent(student, contentItemIds));
        }

        return averageWatchPercentagePerStudent;
    }

    // This private method gets all the contentItemIds that are in the selected course.
    private ArrayList<Integer> getContentItemIds() {
        return contentItemManager.getContentItemIdsByCourse(selectedCourse.getCourseName());
    }

    // This private method gets the contentItemTitlesMap by the contentItemIds.
    private Map<Integer, String> getContentItemTitlesMap(ArrayList<Integer> contentItemIds) {
        return contentItemManager.getContentItemTitlesMap(contentItemIds);
    }

    // This method gets the average watch percentage per contentItem.
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

    // This method gets the watch percentage for a student by the studentId.
    public Map<String, Double> getWatchPercentageForStudent(int studentId){
        Map<String, Double> watchPercentageForStudent = new HashMap<>();

        Map<Integer, String> contentItemTitlesMap = getContentItemTitlesMap(contentItemIds);

        for (int contentItemId : contentItemIds) {
            String contentItemTitle = contentItemTitlesMap.get(contentItemId);
            double watchPercentage = watchProgressManager.getWatchPercentageByStudentAndContentItem(studentId, contentItemId);
            watchPercentageForStudent.put(contentItemTitle, watchPercentage);
        }

        return watchPercentageForStudent;
    }
}