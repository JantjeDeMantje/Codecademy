package com.codecademy.controllers;

import java.util.Date;

import com.codecademy.GUI;
import com.codecademy.dataStorage.DataHolder;
import com.codecademy.domain.Student;
import com.codecademy.logic.StudentManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentScreenController {

    private StudentManager studentManager;

    private Student selectedStudent;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> emailColumn;

    @FXML
    private TableColumn<Student, Date> birthdateColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private Button createBTN;

    @FXML
    private Button deleteBTN;

    @FXML
    private Button registerBTN;

    @FXML
    private Button updateBTN;

    @FXML
    private Button backBTN;

    @FXML
    private TextField nameTEXT;

    @FXML
    private TextField zipcodeTEXT;

    @FXML
    private TextField emailTEXT;

    @FXML
    private TextField genderTEXT;

    @FXML
    private TextField birthdateTEXT;

    @FXML
    private TextField cityTEXT;

    @FXML
    private TextField countryTEXT;

    @FXML
    private TextField addressTEXT;

    public void initialize() {
        studentManager = new StudentManager();
        fillTable();
        tableSelectionListener();
    }

    @FXML
    private void fillTable() { // This method fills the table with the data from the studentManager.

        studentManager.getStudents().forEach(student -> { // Adds all the data from the arraylist to the table.
            studentTable.getItems().add(student);
        });

        // Set cell value factories for each column
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
    }

    private void tableSelectionListener() { // This method looks if a row is selected.
        studentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleRowSelect(newValue);
            }
        });
    }

    private void handleRowSelect(Student selectedStudent) { // This method handels the selected row.
        System.out.println("Selected student: " + selectedStudent.getName()); // Logs the selected studentName

        this.selectedStudent = selectedStudent; // Sets the selected student to the selected student in the table.

        // Sets the textfields to the data of the selected student.
        nameTEXT.setText(selectedStudent.getName());
        emailTEXT.setText(selectedStudent.getEmail());
        birthdateTEXT.setText(selectedStudent.getBirthdate());
        genderTEXT.setText(selectedStudent.getGender());
        zipcodeTEXT.setText(selectedStudent.getZipcode());
        addressTEXT.setText(selectedStudent.getAddress());
        cityTEXT.setText(selectedStudent.getCity());
        countryTEXT.setText(selectedStudent.getCountry());
    }

    @FXML
    private void handleBackButton(ActionEvent event) { // This method handles the back button.
        System.out.println("Back button clicked"); // Logs the activation of the back button.
        GUI.instance.setRoot("homeScreen.fxml"); // Loads the new fxml in.
    }

    @FXML
    void handleCreateButton(ActionEvent event) { // This method handles the create button.

        Student newStudent = studentManager.createStudent(
                nameTEXT.getText(), emailTEXT.getText(),
                birthdateTEXT.getText(), genderTEXT.getText(), addressTEXT.getText(),
                zipcodeTEXT.getText(), cityTEXT.getText(),
                countryTEXT.getText(), studentTable);

        System.out.println("CreateStudentButton pressed: " + newStudent.getName()); // Logs the name of the new student
    }

    @FXML
    void handleDeleteButton(ActionEvent event) { // This method handles the delete button.
        // Logs the name of the deleted student
        System.out.println("DeleteStudentButton pressed: " + selectedStudent.getName());

        studentManager.deleteStudent(selectedStudent, studentTable); // Removes the selected student from the table.
    }

    @FXML
    void handleRegisterButton(ActionEvent event) { // This method handles the register button.
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            System.out.println("No student selected.");
            return;
        }

        System.out.println("Selected student: " + selectedStudent.getName());
        DataHolder.getInstance().setSelectedStudent(selectedStudent);
        GUI.instance.setRoot("StudentRegisterScreen.fxml");
    }

    @FXML
    void handleUpdateButton(ActionEvent event) { // This method handles the update button.
        studentManager.updateStudent(
                selectedStudent, nameTEXT.getText(), emailTEXT.getText(),
                birthdateTEXT.getText(), genderTEXT.getText(), addressTEXT.getText(),
                zipcodeTEXT.getText(), cityTEXT.getText(),
                countryTEXT.getText(), studentTable);

        // Logs the name of the updated student
        System.out.println("UpdateStudentButton pressed: " + selectedStudent.getName());
    }

}
