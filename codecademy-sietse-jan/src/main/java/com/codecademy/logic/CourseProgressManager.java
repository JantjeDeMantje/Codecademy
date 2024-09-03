package com.codecademy.logic;

import java.util.ArrayList;
import java.util.Map;

import com.codecademy.domain.Course;
import com.codecademy.domain.Module;
import com.codecademy.domain.Student;
import com.codecademy.domain.WatchPercentage;


public class CourseProgressManager {

    private ModuleManager moduleManager;
    private WatchProgressManager watchProgressManager;

    private ArrayList<Module> modules;
    private ArrayList<WatchPercentage> watchPercentages;
    private Map<Student, Double> averageWatchPercentages;

    public CourseProgressManager(Course course) {
        loadModuleManager();
        loadWatchProgressManager();
    }

    private void loadModuleManager() { // Loads all modules
        moduleManager = new ModuleManager();
        this.modules = moduleManager.getModules();
    }

    private void loadWatchProgressManager() { // Loads all watchProgresses
        watchProgressManager = new WatchProgressManager();
        this.watchPercentages = watchProgressManager.getWatchPercentages();
        this.averageWatchPercentages = watchProgressManager.getAverageWatchPercentagePerStudent();
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public ArrayList<WatchPercentage> getWatchPercentages() {
        return watchPercentages;
    }

    public Map<Student, Double> getAverageWatchPercentages() {
        return averageWatchPercentages;
    }    
}
