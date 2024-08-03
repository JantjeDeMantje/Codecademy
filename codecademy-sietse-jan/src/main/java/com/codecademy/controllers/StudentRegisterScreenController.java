package com.codecademy.controllers;

import java.util.HashMap;
import java.util.Map;

import com.codecademy.GUI;
import com.codecademy.dataStorage.DataHolder;
import com.codecademy.logic.RegistrationManager;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class StudentRegisterScreenController {

    private RegistrationManager registrationManager;

    private HashMap<String, Boolean> coursesAndRegistrations;

    @FXML
    private TableView<Map.Entry<String,Boolean>> courseRegistrationTable;

    @FXML
    private TableColumn<Map.Entry<String,Boolean>, Boolean> registrationStateColumn;

    @FXML
    private TableColumn<Map.Entry<String,Boolean>, String> courseNameColumn;

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
        
        formatAllInformation(); 

        for (Map.Entry<String,Boolean> entry : coursesAndRegistrations.entrySet()) { 
            courseRegistrationTable.getItems().add(entry);
        }

        // Set cell value factories for each column
        courseNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey()));
        registrationStateColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getValue()));
    }

    private void setLabel() { // This method sets the right student name to the studentNameLabel.
        studentNameLAB.setText(DataHolder.getInstance().getSelectedStudent().getName());
    }

    private void formatAllInformation(){ // This method formats all the information needed for the table.
        coursesAndRegistrations = new HashMap<>();

        registrationManager.getCourses().forEach(course -> {
            coursesAndRegistrations.put(course.getCourseName(), false); // For each course, you put the courseName and a false status.
        });

        registrationManager.getRegistrations().forEach(registration -> { 
            for(Map.Entry<String,Boolean> entry : coursesAndRegistrations.entrySet()){
                if (entry.getKey() == registration.getCourse().getCourseName()){ // When the course has a registration, the status will be true.
                    entry.setValue(true); // Makes the status true
                }
            }
        });
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
