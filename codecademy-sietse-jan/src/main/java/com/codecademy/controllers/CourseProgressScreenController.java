package com.codecademy.controllers;

import com.codecademy.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CourseProgressScreenController {

    @FXML
    private Label achievedCountLAB;

    @FXML
    private Text achievedCountTV;

    @FXML
    private Button backBTN;

    @FXML
    private Label courseNameLAB;

    @FXML
    private Label difficultyLAB;

    @FXML
    private Text difficultyTV;

    @FXML
    private Label introtextLAB;

    @FXML
    private TextFlow introtextTV;

    @FXML
    private TableView<?> moduleProgressTable;

    @FXML
    private TableColumn<?, ?> modulesColumn;

    @FXML
    private TableColumn<?, ?> progressColumn1;

    @FXML
    private TableColumn<?, ?> progressColumn2;

    @FXML
    private TableColumn<?, ?> studentsColumn;

    @FXML
    private TableView<?> studentsProgressTable;

    @FXML
    private Label subjectLAB;

    @FXML
    private Text subjectTV;

    @FXML
    void handleBackButton(ActionEvent event) {// This method handles the back button.
    }

}
