package com.codecademy.dataStorage;

import com.codecademy.domain.Student;
import com.codecademy.domain.Course;

public class DataHolder {
    private Student selectedStudent;

    private Course selectedCourse;

    private static final DataHolder holder = new DataHolder();

    public static DataHolder getInstance() {
        return holder;
    } 

    public void setSelectedStudent(Student selectedStudent){
        this.selectedStudent = selectedStudent;
    }
 
    public Student getSelectedStudent(){
        return selectedStudent;
    }

    public void setSelectedCourse(Course selectedCourse){
        this.selectedCourse = selectedCourse;
    }

    public Course getSelectedCourse(){
        return selectedCourse;
    }
}