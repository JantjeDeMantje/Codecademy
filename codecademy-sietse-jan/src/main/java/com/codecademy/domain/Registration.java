package com.codecademy.domain;

public class Registration {

    private Student student;
    private Course course;
    private String registrationDate;
    private Boolean registered;

    public Registration(Student student, Course course, String registrationDate) {
        this.student = student;
        this.course = course;
        this.registrationDate = registrationDate;
    }

    public Registration(Course course, Boolean registered) {
        this.course = course;
        this.registered = registered;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public Boolean getRegistered() {
        return registered;
    }
}
