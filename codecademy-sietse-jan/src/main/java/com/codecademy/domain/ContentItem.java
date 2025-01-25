package com.codecademy.domain;

public class ContentItem {

    private int contentItemId;
    private String status;
    private String description;
    private String courseName;

    public ContentItem(int contentItemId, String status, String description, String courseName) {
        this.contentItemId = contentItemId;
        this.status = status;
        this.description = description;
        this.courseName = courseName;
    }

    public int getContentItemId() {
        return contentItemId;
    }
    public void setContentItemId(int contentItemId) {
        this.contentItemId = contentItemId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }   
}
