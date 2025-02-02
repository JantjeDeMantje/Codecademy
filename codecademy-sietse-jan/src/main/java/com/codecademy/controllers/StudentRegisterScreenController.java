package com.codecademy.controllers;

import java.util.HashMap;
import java.util.Map;

import com.codecademy.GUI;
import com.codecademy.dataStorage.DataHolder;
import com.codecademy.domain.Course;
import com.codecademy.domain.Student;
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
    private Map.Entry<String,Boolean> selectedRow;
    private Student selectedStudent;

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
        getinformation();
        registrationManager = new RegistrationManager(); // Creates a new registrationManager object.
        setLabel();
        fillTable();
        tableSelectionListener();
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
        studentNameLAB.setText(selectedStudent.getName());
    }

    private void formatAllInformation(){ // This method formats all the information needed for the table.
        coursesAndRegistrations = new HashMap<>();
    
        registrationManager.getCourses().forEach(course -> {
            coursesAndRegistrations.put(course.getCourseName(), false); // For each course, you put the courseName and a false status.
        });
    
        registrationManager.getRegistrationsByStudentId(selectedStudent.getStudentId()).forEach(registration -> { 
            for(Map.Entry<String,Boolean> entry : coursesAndRegistrations.entrySet()){
                if (entry.getKey().equals(registration.getCourseName())){ // When the course has a registration, the status will be true.
                    entry.setValue(true); // Makes the status true
                }
            }
        });
    }

    

    private void getinformation(){ // This method gets all information from the dataholder. (last screen)
        selectedStudent = DataHolder.getInstance().getSelectedStudent();
    }

    private Course getCourseInfo(){ // This method return the Course you got from the manager.
        return registrationManager.getCourseInfo(selectedRow.getKey().toString());
    }



    private Boolean checkRegistration(Boolean checkCondition){ // This method checks if the person is already (un)registered.
        if (checkCondition != selectedRow.getValue()) {
            return true;
        } else {
            return false;
        }
    }



    private void tableSelectionListener() { // This method looks if a row is selected.
        courseRegistrationTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected course: "+ newValue.getKey()); // Logger for the selected courseName
                selectedRow = newValue;

                handleRowSelect(getCourseInfo());
            }
        });
    }

    private void handleRowSelect(Course selectedCourse) { // This method handels the selected row.

        // Sets the textfields to the data of the selected course.
        difficultyTEXT.setText(selectedCourse.getDifficulty().toString());
        subjectTEXT.setText(selectedCourse.getSubject());
        nameTEXT.setText(selectedCourse.getCourseName());
        introTextTEXT.setText(selectedCourse.getIntroductionText());
    }

    @FXML
    void handleRegisterButton(ActionEvent event) { // This method handels the registerButton.
        if(checkRegistration(true)){ // Checks if the person isnt already registerd.
            System.out.println(selectedStudent.getName() + " heeft zich in de cursus " + getCourseInfo().getCourseName() + "geschreven."); // Logs a registration.

            selectedRow.setValue(true); // Sets the state true

            // Let the manager create a registration.
            registrationManager.createRegistration(selectedStudent.getStudentId(), getCourseInfo().getCourseName(), courseRegistrationTable);
        } else {
            System.out.println("Persoon is al geregistreerd bij deze cursus.");
            showAlert("Persoon al ingeschreven", "Deze persoon is al ingeschreven voor deze cursus."); // Shows an alert popup.
        }
    }

    @FXML
    void handleUnregisterButton(ActionEvent event) { // This method handels the unregisterButton.
        if (checkRegistration(false)){ // Checks if the person is registerd.
            System.out.println(selectedStudent.getName() + " heeft zich uit de cursus " + getCourseInfo().getCourseName() + "geschreven."); // Logs a unregistration.

            selectedRow.setValue(false); // Sets the state false
            
            // let the manager delete a registration.
            registrationManager.deleteRegistration(selectedStudent.getStudentId(), getCourseInfo().getCourseName(), courseRegistrationTable);
        } else {
            System.out.println("Persoon is niet geregistreerd bij deze cursus.");
            showAlert("Persoon al uitgeschreven", "Deze persoon is al uitgeschreven voor deze cursus, of was nooit ingeschreven."); // Shows an alert popup.
        }
    }

    @FXML
    void handleBackButton(ActionEvent event) {
        System.out.println("Back button clicked"); // Logs the activation of the back button.
        GUI.instance.setRoot("studentScreen.fxml"); // Loads the new fxml in.
    }

    private void showAlert(String title, String message) { // This method shows an alert popup.
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
