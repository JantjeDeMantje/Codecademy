package com.codecademy.controllers;

import com.codecademy.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class StatisticsScreenController {
   
    @FXML
    private Button backBTN;

    @FXML
    private Label statsLAB;

    @FXML
    private Text viewcount1TEXT;

    @FXML
    private Text viewcount2TEXT;

    @FXML
    private Text viewcount3TEXT;

    @FXML
    private Text webcast1TEXT;

    @FXML
    private Text webcast2TEXT;

    @FXML
    private Text webcast3TEXT;

    @FXML
    void handleBackButton(ActionEvent event) {
        System.out.println("Back button clicked"); // Logs the activation of the back button.
        GUI.instance.setRoot("homeScreen.fxml"); // Loads the new fxml in.
    }
}
