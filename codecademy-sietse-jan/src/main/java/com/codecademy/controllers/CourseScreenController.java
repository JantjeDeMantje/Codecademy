package com.codecademy.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.codecademy.GUI;
import com.codecademy.domain.Course;
import com.codecademy.logic.CourseManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class CourseScreenController implements Initializable {

    private CourseManager courseManager;

    @FXML
    private Button backBTN;

    @FXML
    private Text courseTV;

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, String> courseColumn;

    @FXML
    private TableColumn<Course, String> difficultyColumn;

    @FXML
    private TableColumn<Course, String> subjectColumn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) { // This method initializes the course screen.
        courseManager = new CourseManager(); // Creates a new courseManager object.
        fillTable();
        tableSelectionListener();
    }

    @FXML
    private void fillTable() { // This method fills the table with the data from the courseManager.

        courseManager.getCourses().forEach(course -> { // Adds all the data from the arraylist to the table.
            courseTable.getItems().add(course);
        });

        // Set cell value factories for each column
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        difficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
    }

    @FXML
    private void handleBackButton(ActionEvent event) { // This method handles the back button.
        System.out.println("Back button clicked"); // Logs the activation of the back button.
        GUI.instance.setRoot("homeScreen.fxml"); // Loads the new fxml in.
    }

    private void tableSelectionListener() { // This method looks if a row is selected.
        courseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleRowSelect(newValue);
            }
        });
    }

    private void handleRowSelect(Course selectedCourse) { // This method handels the selected row.
        System.out.println("Selected course: " + selectedCourse.getCourseName()); // Logs the selected courseName

        GUI.instance.setRoot("CourseProgressScreen.fxml"); // Loads the new fxml in.
    }
}
