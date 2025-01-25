package com.codecademy.logic;

import com.codecademy.dataStorage.WatchProgressDAO;

public class WatchProgressManager {

    private WatchProgressDAO watchProgressDAO;

    public WatchProgressManager() {
        watchProgressDAO = new WatchProgressDAO();
    }

    public double getAverageWatchPercentageByStudent(int studentId){
        return watchProgressDAO.getAverageWatchPercentageByStudent(studentId);
    }

}
