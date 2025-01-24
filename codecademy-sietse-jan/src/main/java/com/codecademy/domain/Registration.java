package com.codecademy.domain;

public class Registration {

    private int studentId;
    private String courseName;
    private String registrationDate;

    public Registration(int studentId, String courseName, String registrationDate) {
        this.studentId = studentId;
        this.courseName = courseName;
        this.registrationDate = registrationDate;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

}
