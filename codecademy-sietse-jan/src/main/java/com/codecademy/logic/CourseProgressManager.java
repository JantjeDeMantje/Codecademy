package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.domain.Course;
import com.codecademy.domain.Module;
import com.codecademy.domain.Student;
import com.codecademy.domain.WatchPercentage;


public class CourseProgressManager {

    private ModuleManager moduleManager;
    private WatchProgressManager watchProgressManager;

    private ArrayList<Module> modules;
    private ArrayList<WatchPercentage> watchPercentages;

    public CourseProgressManager(Course course) {
        loadModuleManager();
        loadWatchProgressManager();
    }

    private void loadModuleManager() {
        moduleManager = new ModuleManager();
        this.modules = moduleManager.getModules();
    }

    private void loadWatchProgressManager() {
        watchProgressManager = new WatchProgressManager();
        this.watchPercentages = watchProgressManager.getWatchPercentages();

    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public ArrayList<WatchPercentage> getWatchPercentages() {
        return watchPercentages;
    }

    




    
}
