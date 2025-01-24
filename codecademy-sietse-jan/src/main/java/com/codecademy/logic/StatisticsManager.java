package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.dataStorage.WebcastDAO;
import com.codecademy.domain.Webcast;

public class StatisticsManager {
    private WebcastDAO webcastDAO;

    public StatisticsManager() {
        webcastDAO = new WebcastDAO();
    }

    public ArrayList<Webcast> getTop3Webcasts() {
        return webcastDAO.getTop3Webcasts();
    }
}
