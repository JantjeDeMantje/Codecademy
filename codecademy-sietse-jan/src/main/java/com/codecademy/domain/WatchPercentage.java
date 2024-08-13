package com.codecademy.domain;

public class WatchPercentage {

    private Student student;
    private Course course;
    private double watchPercentage;

    public WatchPercentage(Student student, Course course, double watchPercentage) {
        this.student = student;
        this.course = course;
        this.watchPercentage = watchPercentage;
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

    public double getWatchPercentage() {
        return watchPercentage;
    }

    public void setWatchPercentage(double watchPercentage) {
        this.watchPercentage = watchPercentage;
    }
    
}
