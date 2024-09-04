package com.codecademy.controllers;

import java.util.Map;

import com.codecademy.GUI;
import com.codecademy.dataStorage.DataHolder;
import com.codecademy.domain.Course;
import com.codecademy.domain.WatchPercentage;
import com.codecademy.logic.CourseProgressManager;
import com.codecademy.domain.Module;
import com.codecademy.domain.Student;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CourseProgressScreenController {

    private Course selectedCourse;

    private CourseProgressManager courseProgressManager;

    @FXML
    private Label achievedCountLAB;

    @FXML
    private TextField achievedCountTV;

    @FXML
    private Button backBTN;

    @FXML
    private Label courseNameLAB;

    @FXML
    private Label difficultyLAB;

    @FXML
    private TextField difficultyTV;

    @FXML
    private Label introtextLAB;

    @FXML
    private TextArea introtextTV;

    @FXML
    private TableView<Module> moduleProgressTable;

    @FXML
    private TableColumn<Module, String> modulesColumn;

    @FXML
    private TableColumn<WatchPercentage, String> progressColumn1;

    @FXML
    private TableColumn<Module, String> progressColumn2;

    @FXML
    private TableColumn<WatchPercentage, String> studentsColumn;

    @FXML
    private TableView<WatchPercentage> studentsProgressTable;

    @FXML
    private Label subjectLAB;

    @FXML
    private TextField subjectTV;

    public void initialize() { // This method initializes the course progress screen.
        getinformation();
        courseProgressManager = new CourseProgressManager(selectedCourse); // Creates a new courseProgressManager object.
        setLabels();
        fillStudentsTable();
        fillModulesTable();

        tableSelectionListener();
    }

    private void getinformation() { // This method gets all information from the dataholder. (last screen)
        selectedCourse = DataHolder.getInstance().getSelectedCourse();
    }

    private void setLabels() { // This method sets all the labels with the information from the selected
                               // course.
        courseNameLAB.setText(selectedCourse.getCourseName());
        difficultyTV.setText(selectedCourse.getDifficulty().toString());
        subjectTV.setText(selectedCourse.getSubject());
        introtextTV.setText(selectedCourse.getIntroductionText());
        achievedCountTV.setText(String.valueOf(10 + (int) ((100 - 10 + 10) * Math.random())));
    }

    private void fillStudentsTable() { // This method fills the table with the data from the courseProgressManager.
        Map<Student, Double> averageWatchPercentages = courseProgressManager.getAverageWatchPercentagePerStudent(); // Gets the average watch percentages from the courseProgressManager.

        averageWatchPercentages.forEach((student, averagePercentage) -> {
            WatchPercentage wp = new WatchPercentage(student, null, averagePercentage); // Makes a new watchPercentage object, so it can be added to the table.
            studentsProgressTable.getItems().add(wp);
        });

        studentsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudent().getName()));
        progressColumn1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWatchPercentage() + "%"));
    }

    private void fillModulesTable() { // This method fills the table with the data from the courseProgressManager.
        Map<Module, Double> averageWatchPercentages = courseProgressManager.getAverageWatchPercentagePerModule(); // Gets the average watch percentages from the courseProgressManager.

        averageWatchPercentages.forEach((module, averagePercentage) -> { // Adds the module to the table.
            moduleProgressTable.getItems().add(module);
        });

        modulesColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        progressColumn2.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(averageWatchPercentages.get(cellData.getValue())) + "%"));
    }  

    private void tableSelectionListener() { // This method looks if a row is selected.
        studentsProgressTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleRowSelect(newValue);
            }
        });
    }

    private void handleRowSelect(WatchPercentage selectedWatchPercentage) { // This method handels the selected row.
        System.out.println("Selected student: " + selectedWatchPercentage.getStudent().getName()); // Logs the selected studentName
    }

    @FXML
    void handleBackButton(ActionEvent event) {// This method handles the back button.
        System.out.println("Back button clicked --> courseScreen"); // Logs the activation of the back button.
        GUI.instance.setRoot("courseScreen.fxml"); // Loads the new fxml in.
    }

}
