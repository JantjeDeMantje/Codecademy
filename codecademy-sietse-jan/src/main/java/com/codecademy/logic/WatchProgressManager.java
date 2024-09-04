package com.codecademy.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public Map<Student, Double> getAverageWatchPercentagePerStudent() { // Calculates the average watch percentage per student.
        Map<Student, Double> averageWatchPercentages = new HashMap<>();
        Map<Student, Double> totalWatchPercentages = new HashMap<>();
        Map<Student, Integer> countWatchPercentages = new HashMap<>();

        for (WatchPercentage wp : watchPercentages) { // Reads all watch percentages and adds them to the total and count per Student.
            Student student = wp.getStudent();
            double percentage = wp.getWatchPercentage();

            totalWatchPercentages.put(student, totalWatchPercentages.getOrDefault(student, 0.0) + percentage);
            countWatchPercentages.put(student, countWatchPercentages.getOrDefault(student, 0) + 1);
        }

        for (Student student : totalWatchPercentages.keySet()) { // Calculates the average watch percentage per student.
            double total = totalWatchPercentages.get(student);
            int count = countWatchPercentages.get(student);
            double average = Math.round((total / count) * 10.0) / 10.0;
            averageWatchPercentages.put(student, average);
        }

        return averageWatchPercentages;
    }

    public Map<Module, Double> getAverageWatchPercentagePerModule() { // Calculates the average watch percentage per module.
        Map<Module, Double> averageWatchPercentages = new HashMap<>();
        Map<Module, Double> totalWatchPercentages = new HashMap<>();
        Map<Module, Integer> countWatchPercentages = new HashMap<>();
    
        for (WatchPercentage wp : watchPercentages) { // Reads all watch percentages and adds them to the total and count per Module.
            Module module = wp.getModule();
            double percentage = wp.getWatchPercentage();
    
            totalWatchPercentages.put(module, totalWatchPercentages.getOrDefault(module, 0.0) + percentage);
            countWatchPercentages.put(module, countWatchPercentages.getOrDefault(module, 0) + 1);
        }
    
        for (Module module : totalWatchPercentages.keySet()) { // Calculates the average watch percentage per module.
            double total = totalWatchPercentages.get(module);
            int count = countWatchPercentages.get(module);
            double average = Math.round((total / count) * 10.0) / 10.0;
            averageWatchPercentages.put(module, average);
        }
    
        return averageWatchPercentages;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }  
}
