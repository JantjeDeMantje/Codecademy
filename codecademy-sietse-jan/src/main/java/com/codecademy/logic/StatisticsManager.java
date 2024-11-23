package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.domain.Webcast;

public class StatisticsManager {
    private ArrayList<Webcast> top3Webcasts;

    public StatisticsManager() {
        createDummyData();
    }

    private void createDummyData() { // This method creates the temp. dummy data
        top3Webcasts = new ArrayList<Webcast>();

        top3Webcasts.add(new Webcast("NX workshop Guide", "Een video over hoe NX workshop werkt", "Sietse", "Microsoft", 678, "youtube.com/dkawoke", 67498));
        top3Webcasts.add(new Webcast("Java Basics", "Introductie tot Java programmeren", "Jan", "Codecademy", 1234, "youtube.com/javabasics", 58342));
        top3Webcasts.add(new Webcast("Advanced Python", "Geavanceerde technieken in Python", "Piet", "Apple", 5678, "youtube.com/advancedpython", 38742));
    }

    public ArrayList<Webcast> getTop3Webcasts() {
        return top3Webcasts;
    }
}
