package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.dataStorage.WatchProgressDAO;

public class WatchProgressManager {

    private WatchProgressDAO watchProgressDAO;

    public WatchProgressManager() {
        watchProgressDAO = new WatchProgressDAO();
    }

    public double getAverageWatchPercentageByStudent(int studentId, ArrayList<Integer> contentItemIds){
        return watchProgressDAO.getAverageWatchPercentageByStudent(studentId, contentItemIds);
    }

    public double getAverageWatchPercentageByContentItem(int contentItemId){
        return watchProgressDAO.getAverageWatchPercentageByContentItem(contentItemId);
    }

}
