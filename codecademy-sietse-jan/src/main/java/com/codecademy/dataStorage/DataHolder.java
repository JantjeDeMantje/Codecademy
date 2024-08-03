package com.codecademy.dataStorage;

import com.codecademy.domain.Student;

public class DataHolder {
    private Student selectedStudent;

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
}