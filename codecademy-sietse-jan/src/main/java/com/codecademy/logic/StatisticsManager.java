package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.dataStorage.WebcastDAO;
import com.codecademy.domain.Webcast;


// This class is the manager of the Statistics It is responsible for the communication between the DAO and other classes.
public class StatisticsManager {
    private WebcastDAO webcastDAO;

    public StatisticsManager() {
        webcastDAO = new WebcastDAO();
    }

    // This method returns the top 3 webcasts
    public ArrayList<Webcast> getTop3Webcasts() {
        return webcastDAO.getTop3Webcasts();
    }
}
