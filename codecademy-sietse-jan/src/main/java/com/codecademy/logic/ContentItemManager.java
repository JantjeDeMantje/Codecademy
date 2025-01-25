package com.codecademy.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.codecademy.dataStorage.ContentItemDAO;

public class ContentItemManager {
    private ContentItemDAO contentItemDAO;
    
    public ContentItemManager() {
        contentItemDAO = new ContentItemDAO();
    }

    public ArrayList<Integer> getContentItemIdsByCourse(String courseName) {
        return contentItemDAO.getContentItemIdsByCourse(courseName);
    }

    public Map<Integer, String> getContentItemTitlesMap(ArrayList<Integer> contentItemIds) {
        Map<Integer, String> contentItemTitlesMap = new HashMap<>();
        contentItemTitlesMap.putAll(contentItemDAO.getModulesTitlesMap(contentItemIds));
        contentItemTitlesMap.putAll(contentItemDAO.getWebcastTitlesMap(contentItemIds));
        return contentItemTitlesMap;
    }
}
