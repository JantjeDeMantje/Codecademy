package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.dataStorage.WatchProgressDAO;


// This class is the manager of the watchprocess. It is responsible for the communication between the DAO and other classes.
public class WatchProgressManager {

    private WatchProgressDAO watchProgressDAO;

    public WatchProgressManager() {
        watchProgressDAO = new WatchProgressDAO();
    }

    // This method returns the average watch percentage of a student.
    protected double getAverageWatchPercentageByStudent(int studentId, ArrayList<Integer> contentItemIds){
        double average = watchProgressDAO.getAverageWatchPercentageByStudent(studentId, contentItemIds);
        return Math.round(average * 10.0) / 10.0;
    }

    // This method returns the average watch percentage of a content item.
    protected double getAverageWatchPercentageByContentItem(int contentItemId){
        double average = watchProgressDAO.getAverageWatchPercentageByContentItem(contentItemId);
        return Math.round(average * 10.0) / 10.0;
    }

    // This method returns the watch percentage of a student and a content item.
    protected double getWatchPercentageByStudentAndContentItem(int studentId, int contentItemId){
        double average = watchProgressDAO.getWatchPercentageByStudentAndContentItem(studentId, contentItemId);
        return Math.round(average * 10.0) / 10.0;
    }

}
