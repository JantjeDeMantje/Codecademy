package com.codecademy.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.codecademy.dataStorage.ContentItemDAO;

// This class is the manager of the ContentItems. It is responsible for the communication between the DAO and other classes.
public class ContentItemManager {
    private ContentItemDAO contentItemDAO;
    
    public ContentItemManager() { 
        contentItemDAO = new ContentItemDAO(); // Create a new ContentItemDAO object
    }

    // Get the content item ids by course name
    protected ArrayList<Integer> getContentItemIdsByCourse(String courseName) {
        return contentItemDAO.getContentItemIdsByCourse(courseName);
    }

    // Get the content item titles by content item ids
    protected Map<Integer, String> getContentItemTitlesMap(ArrayList<Integer> contentItemIds) {
        Map<Integer, String> contentItemTitlesMap = new HashMap<>();
        contentItemTitlesMap.putAll(contentItemDAO.getModulesTitlesMap(contentItemIds));
        contentItemTitlesMap.putAll(contentItemDAO.getWebcastTitlesMap(contentItemIds));
        return contentItemTitlesMap;
    }
}
