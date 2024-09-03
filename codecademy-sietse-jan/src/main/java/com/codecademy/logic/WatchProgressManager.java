package com.codecademy.logic;

import java.util.ArrayList;

import com.codecademy.domain.Student;
import com.codecademy.domain.WatchPercentage;
import com.codecademy.domain.Module;

public class WatchProgressManager {

    private ArrayList<WatchPercentage> watchPercentages;
    private ArrayList<Student> students;
    private ArrayList<Module> modules;
    private StudentManager studentManager;
    private ModuleManager moduleManager;

    public WatchProgressManager() {
        watchPercentages = new ArrayList<>();
        loadStudentManager();
        loadModuleManager();
        createDummyData();
        getAverageWatchPercentagePerStudent();
    }

    private void createDummyData() { // Creates temp. dummy data for testing purposes.
        for (Student student : students) {
            for (Module module : modules) {
                watchPercentages.add(new WatchPercentage(student, module, Math.round(Math.random() * 100.0)));
            }
        }
    }

    private void loadStudentManager() { // Loads all students
        studentManager = new StudentManager();
        this.students = studentManager.getStudents();
    }

    private void loadModuleManager() { // Loads all modules
        moduleManager = new ModuleManager();
        this.modules = moduleManager.getModules();
    }

    public ArrayList<WatchPercentage> getWatchPercentages() {
        return watchPercentages;
    }
    
}
