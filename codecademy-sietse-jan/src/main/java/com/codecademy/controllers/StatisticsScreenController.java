package com.codecademy.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.codecademy.GUI;
import com.codecademy.logic.CourseManager;
import com.codecademy.logic.StatisticsManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StatisticsScreenController implements Initializable{
   
    private StatisticsManager statisticsManager;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) { // This method initializes the statistics screen.
        statisticsManager = new StatisticsManager(); // Creates a new statisticsManager object.
        fillStage();
    }

    private void fillStage() { // This method fills the stage with the data from the statisticsManager.
    }

    @FXML
    void handleBackButton(ActionEvent event) { // This method handles the back button.
        System.out.println("Back button clicked"); // Logs the activation of the back button.
        GUI.instance.setRoot("homeScreen.fxml"); // Loads the new fxml in.
    }
}
