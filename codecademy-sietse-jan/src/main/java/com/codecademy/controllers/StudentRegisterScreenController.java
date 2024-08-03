package com.codecademy.controllers;

import java.util.List;

import com.codecademy.GUI;
import com.codecademy.dataStorage.DataHolder;
import com.codecademy.domain.Registration;
import com.codecademy.logic.RegistrationManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class StudentRegisterScreenController {

    private RegistrationManager registrationManager;

    @FXML
    private TableView<Registration> courseRegistrationTable;

    @FXML
    private TableColumn<Registration, CheckBox> registrationStateColumn;

    @FXML
    private TableColumn<Registration, String> courseNameColumn;

    @FXML
    private TextField difficultyTEXT;

    @FXML
    private TextField subjectTEXT;

    @FXML
    private TextField nameTEXT;

    @FXML
    private TextArea introTextTEXT;

    @FXML
    private Button registerBTN;

    @FXML
    private Button unregisterBTN;

    @FXML
    private Button backBTN;

    @FXML
    private Text studentNameLAB;

    public void initialize() { // This method initializes the course screen.
        registrationManager = new RegistrationManager(); // Creates a new registrationManager object.
        setLabel();
        fillTable();
    }

    @FXML
    private void fillTable() { // This method fills the table with the data from the registrationManager.
        List<Registration> registrations = registrationManager.getRegistrations();

        registrations.forEach(registration -> { // Adds all the data from the arraylist to the table.
            courseRegistrationTable.getItems().add(registration);
        });

        // Set cell value factories for each column
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        registrationStateColumn.setCellValueFactory(new PropertyValueFactory<>("registered"));
    }

    private void setLabel() {
        studentNameLAB.setText(DataHolder.getInstance().getSelectedStudent().getName());
    }

    @FXML
    void handleRegisterButton(ActionEvent event) {

    }

    @FXML
    void handleUnregisterButton(ActionEvent event) {

    }

    @FXML
    void handleBackButton(ActionEvent event) {
        System.out.println("Back button clicked"); // Logs the activation of the back button.
        GUI.instance.setRoot("studentScreen.fxml"); // Loads the new fxml in.

    }
}
