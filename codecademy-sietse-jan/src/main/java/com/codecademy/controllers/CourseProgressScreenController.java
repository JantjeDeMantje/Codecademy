package com.codecademy.controllers;

import com.codecademy.GUI;
import com.codecademy.dataStorage.DataHolder;
import com.codecademy.domain.Course;
import com.codecademy.domain.WatchPercentage;
import com.codecademy.logic.CourseProgressManager;
import com.codecademy.domain.Module;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn<Module, Integer> modulesColumn;

    @FXML
    private TableColumn<WatchPercentage, Integer> progressColumn1;

    @FXML
    private TableColumn<Module, Integer> progressColumn2;

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
        courseProgressManager.getWatchPercentages().forEach(watchPercentage -> { // Adds all the data from the arraylist to the table.
            studentsProgressTable.getItems().add(watchPercentage);

        });

        studentsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudent().getName()));
        progressColumn1.setCellValueFactory(new PropertyValueFactory<>("watchPercentage"));
    }

    private void fillModulesTable() { // This method fills the table with the data from the courseProgressManager.
        courseProgressManager.getModules().forEach(module -> { // Adds all the data from the arraylist to the table.
            moduleProgressTable.getItems().add(module);
        });

        modulesColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    }


    @FXML
    void handleBackButton(ActionEvent event) {// This method handles the back button.
        System.out.println("Back button clicked --> courseScreen"); // Logs the activation of the back button.
        GUI.instance.setRoot("courseScreen.fxml"); // Loads the new fxml in.
    }

}
