package com.codecademy.controllers;

import com.codecademy.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

// This class is the controller for the homeScreen and makes all the buttons work.
public class HomeScreenController {

    @FXML
    private Button studentBTN;

    @FXML
    private Button courseBTN;

    @FXML
    private Button statsBTN;

    @FXML
    private Text titleTV;

    @FXML
    private void handleStudentButton(ActionEvent event) { // This method handles the student button.
        System.out.println("Students button clicked"); // Logs the activation of the student button.

        GUI.instance.setRoot("studentScreen.fxml"); // Loads the new fxml in.
    }

    @FXML
    private void handleCourseButton(ActionEvent event) { // This method handles the course button.
        System.out.println("Course button clicked"); // Logs the activation of the course button.

        GUI.instance.setRoot("courseScreen.fxml"); // Loads the new fxml in.
    }

    @FXML
    private void handleStatsButton(ActionEvent event) { // This method handles the stats button.
        System.out.println("Stats button clicked"); // Logs the activation of the stats button.

        GUI.instance.setRoot("statisticsScreen.fxml"); // Loads the new fxml in.
    }

}
