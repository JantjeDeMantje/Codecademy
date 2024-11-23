package com.codecademy.domain;

public class Webcast {
    
    private String title;
    private String description;
    private String speakerName;
    private String speakerOrganisation;
    private int timeLength;
    private String url;
    private int views;

    public Webcast(String title, String description, String speakerName, String speakerOrganisation, int timeLength, String url, int views) {
        this.title = title;
        this.description = description;
        this.speakerName = speakerName;
        this.speakerOrganisation = speakerOrganisation;
        this.timeLength = timeLength;
        this.url = url;
        this.views = views;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getSpeakerOrganisation() {
        return speakerOrganisation;
    }

    public void setSpeakerOrganisation(String speakerOrganisation) {
        this.speakerOrganisation = speakerOrganisation;
    }

    public int getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
