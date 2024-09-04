package com.codecademy.logic;

import java.util.ArrayList;
import java.util.Map;

import com.codecademy.domain.Course;
import com.codecademy.domain.Module;
import com.codecademy.domain.Student;
import com.codecademy.domain.WatchPercentage;


public class CourseProgressManager {

    private WatchProgressManager watchProgressManager;

    private ArrayList<Module> modules;
    private ArrayList<WatchPercentage> watchPercentages;
    private Map<Student, Double> averageWatchPercentagePerStudent;
    private Map<Module, Double> averageWatchPercentagePerModule;

    public CourseProgressManager(Course course) {
        loadWatchProgressManager();
    }

    private void loadWatchProgressManager() { // Loads all watchProgresses
        watchProgressManager = new WatchProgressManager();
        this.watchPercentages = watchProgressManager.getWatchPercentages();
        this.averageWatchPercentagePerStudent = watchProgressManager.getAverageWatchPercentagePerStudent();
        this.averageWatchPercentagePerModule = watchProgressManager.getAverageWatchPercentagePerModule();

        this.modules = watchProgressManager.getModules(); // Loads all the modules from the ModuleManager.
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public ArrayList<WatchPercentage> getWatchPercentages() {
        return watchPercentages;
    }

    public Map<Student, Double> getAverageWatchPercentagePerStudent() {
        return averageWatchPercentagePerStudent;
    }    

    public Map<Module, Double> getAverageWatchPercentagePerModule(){
        return averageWatchPercentagePerModule;
    }

    public Map<Module, Double> getWatchPercentageForStudent(Student student) {
        return watchProgressManager.getWatchPercentageForStudent(student);
    }
}
