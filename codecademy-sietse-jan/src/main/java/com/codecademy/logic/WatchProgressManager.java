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
    public double getAverageWatchPercentageByStudent(int studentId, ArrayList<Integer> contentItemIds){
        return watchProgressDAO.getAverageWatchPercentageByStudent(studentId, contentItemIds);
    }

    // This method returns the average watch percentage of a content item.
    public double getAverageWatchPercentageByContentItem(int contentItemId){
        return watchProgressDAO.getAverageWatchPercentageByContentItem(contentItemId);
    }

    // This method returns the watch percentage of a student and a content item.
    public double getWatchPercentageByStudentAndContentItem(int studentId, int contentItemId){
        return watchProgressDAO.getWatchPercentageByStudentAndContentItem(studentId, contentItemId);
    }

}
