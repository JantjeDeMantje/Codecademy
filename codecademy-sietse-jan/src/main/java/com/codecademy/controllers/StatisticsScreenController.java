package com.codecademy.controllers;

import com.codecademy.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StatisticsScreenController {
   
    @FXML
    private Button backBTN;

    @FXML
    private Label statsLAB;

    @FXML
    void handleBackButton(ActionEvent event) {
        System.out.println("Back button clicked"); // Logs the activation of the back button.
        GUI.instance.setRoot("homeScreen.fxml"); // Loads the new fxml in.
    }
}
