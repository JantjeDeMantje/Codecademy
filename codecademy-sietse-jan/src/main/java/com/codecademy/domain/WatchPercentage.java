package com.codecademy.domain;

public class WatchPercentage {

    private Student student;
    private double watchPercentage;
    private Module module;

    public WatchPercentage(Student student, Module module, double watchPercentage) {
        this.student = student;
        this.module = module;
        this.watchPercentage = watchPercentage;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public double getWatchPercentage() {
        return watchPercentage;
    }

    public void setWatchPercentage(double watchPercentage) {
        this.watchPercentage = watchPercentage;
    }
    
}
