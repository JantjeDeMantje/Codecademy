package com.codecademy.domain;

public class WatchPercentage {

    private int studentId;
    private double watchPercentage;
    private int contentItemId;
    
    public WatchPercentage(int studentId, double watchPercentage, int contentItemId) {
        this.studentId = studentId;
        this.watchPercentage = watchPercentage;
        this.contentItemId = contentItemId;
    }
    
    public int getStudentId() {
        return studentId;
    }

    public double getWatchPercentage() {
        return watchPercentage;
    }

    public int getContentItemId() {
        return contentItemId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setWatchPercentage(double watchPercentage) {
        this.watchPercentage = watchPercentage;
    }

    public void setContentItemId(int contentItemId) {
        this.contentItemId = contentItemId;
    }
}
