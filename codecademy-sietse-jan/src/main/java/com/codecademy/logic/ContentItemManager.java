package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.dataStorage.ContentItemDAO;

public class ContentItemManager {
    private ContentItemDAO contentItemDAO;
    
    public ContentItemManager() {
        contentItemDAO = new ContentItemDAO();
    }

    public ArrayList<Integer> getContentItemIdsByCourse(String courseName) {
        return contentItemDAO.getContentItemIdsByCourse(courseName);
    }

    public ArrayList<String> getContentItemTitles(ArrayList<Integer> contentItemIds) {
        ArrayList<String> modules = contentItemDAO.getModulesTitles(contentItemIds);
        ArrayList<String> webcasts = contentItemDAO.getWebcastTitles(contentItemIds);
        ArrayList<String> allTitles = new ArrayList<>();
        allTitles.addAll(modules);
        allTitles.addAll(webcasts);
        return allTitles; 
    }
}
