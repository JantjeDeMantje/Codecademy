package com.codecademy.controllers;

import java.util.Map;

import com.codecademy.GUI;
import com.codecademy.dataStorage.DataHolder;
import com.codecademy.domain.ContentItem;
import com.codecademy.domain.Course;
import com.codecademy.domain.WatchPercentage;
import com.codecademy.logic.CourseProgressManager;
import com.codecademy.domain.Module;
import com.codecademy.domain.Student;

import javafx.util.Pair;

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
    private Button refreshBTN;

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
    private TableView<String> contentitemsProgressTable;

    @FXML
    private TableColumn<String, String> contentitemsColumn;

    @FXML
    private TableColumn<WatchPercentage, String> progressColumn1;

    @FXML
    private TableColumn<String, String> progressColumn2;

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
        fillContentItemsTable();

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
        achievedCountTV.setText(String.valueOf(10 + (int) ((100 - 10 + 10) * Math.random()))); // We dont save certificates, so we made this number random.
    }

    private void fillStudentsTable() { // This method fills the table with the data from the courseProgressManager.

        Map<Student, Double> averageWatchPercentages = courseProgressManager.getAverageWatchPercentagePerStudent(); // Gets the average watch percentages from the courseProgressManager.
    
        averageWatchPercentages.forEach((student, averagePercentage) -> {
            WatchPercentage wp = new WatchPercentage(student.getStudentId(), averagePercentage, 1); // Makes a new watchPercentage object, so it can be added to the table.
            studentsProgressTable.getItems().add(wp);
        });
    
        studentsColumn.setCellValueFactory(cellData -> {
            WatchPercentage wp = cellData.getValue();
            Student student = courseProgressManager.getStudentInfoById(wp.getStudentId());
            return new SimpleStringProperty(student.getName()); // Use student name instead of student ID
        });
        progressColumn1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWatchPercentage() + "%"));
    }
        
    private void fillContentItemsTable() { // This method fills the table with the data from the courseProgressManager.
    Map<String, Double> averageWatchPercentages = courseProgressManager.getAverageWatchPercentagePerContentItem(); // Gets the average watch percentages from the courseProgressManager.
    
    averageWatchPercentages.forEach((contentItemTitle, averagePercentage) -> { // Adds the content item to the table.
        contentitemsProgressTable.getItems().add(contentItemTitle);
    });
    
    contentitemsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
    progressColumn2.setCellValueFactory(cellData -> {
        String contentItemId = cellData.getValue();
        Double averagePercentage = averageWatchPercentages.get(contentItemId);
        return new SimpleStringProperty(averagePercentage + "%");
    });
    }

    private void tableSelectionListener() { // This method looks if a row is selected.
        studentsProgressTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleRowSelect(newValue);
            }
        });
    }

    private void handleRowSelect(WatchPercentage selectedWatchPercentage) { // This method handles the selected row.

        System.out.println("Selected student: " + selectedWatchPercentage.getStudentId()); // Logs the selected student ID

        contentitemsProgressTable.getItems().clear();

        Map<String, Double> watchPercentageForStudent = courseProgressManager.getWatchPercentageForStudent(selectedWatchPercentage.getStudentId()); // Gets the watch percentage for the selected student.

        watchPercentageForStudent.forEach((contentItemTitle, watchPercentage) -> { // Adds the content item to the table.
            contentitemsProgressTable.getItems().add(contentItemTitle);
        });

        contentitemsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        progressColumn2.setCellValueFactory(cellData -> {
            String contentItemId = cellData.getValue();
            Double watchPercentage = watchPercentageForStudent.get(contentItemId);
            return new SimpleStringProperty(watchPercentage + "%");
        });

        progressColumn2.setText("Voortgang student");
    }

    
    @FXML
    void handleBackButton(ActionEvent event) {// This method handles the back button.
        System.out.println("Back button clicked --> courseScreen"); // Logs the activation of the back button.
        GUI.instance.setRoot("courseScreen.fxml"); // Loads the new fxml in.
    }

    @FXML
    void handleRefreshButton(ActionEvent event) { // This method handles the refresh button.
        System.out.println("Refresh button clicked"); // Logs the activation of the refresh button.

        contentitemsProgressTable.getItems().clear(); // Clears the table.
        fillContentItemsTable(); // Fills the table again.
        progressColumn2.setText("Gem. Voortgang");
    }
}