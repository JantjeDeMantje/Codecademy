package com.codecademy.controllers;

import java.util.Date;

import com.codecademy.GUI;
import com.codecademy.domain.Course;
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
    private TextField postcodeTEXT;

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

    public void initialize() {
        studentManager = new StudentManager();
        fillTable();
        tableSelectionListener();
    }

    @FXML
    private void fillTable() { // This method fills the table with the data from the courseManager.

        studentManager.getStudents().forEach(student -> { // Adds all the data from the arraylist to the table.
            studentTable.getItems().add(student);
        });

        // Set cell value factories for each column
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
    }

    @FXML
    private void handleBackButton(ActionEvent event) { // This method handles the back button.
        System.out.println("Back button clicked"); // Logs the activation of the back button.
        GUI.instance.setRoot("homeScreen.fxml"); // Loads the new fxml in.
    }

    private void tableSelectionListener() { // This method looks if a row is selected.
        studentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleRowSelect(newValue);
            }
        });
    }

    private void handleRowSelect(Student selectedStudent) { // This method handels the selected row.
        System.out.println("Selected student: " + selectedStudent.getName()); // Logs the selected courseName

        nameTEXT.setText(selectedStudent.getName());
        emailTEXT.setText(selectedStudent.getEmail());
        birthdateTEXT.setText(selectedStudent.getBirthdate());
        genderTEXT.setText(selectedStudent.getGender());
        postcodeTEXT.setText(selectedStudent.getPostcode());
        cityTEXT.setText(selectedStudent.getCity());
        countryTEXT.setText(selectedStudent.getCountry());
    }

    @FXML
    void handleCreateButton(ActionEvent event) {
        String studentName = nameTEXT.getText();
        String studentEmail = emailTEXT.getText();
        String studentBirthdate = birthdateTEXT.getText();
        String studentGender = genderTEXT.getText();
        String studentPostcode = postcodeTEXT.getText();
        String studentCity = cityTEXT.getText();
        String studentCountry = countryTEXT.getText();

        Student newStudent = new Student(studentName, studentEmail, studentBirthdate, studentGender, studentPostcode, studentCity, studentCountry);
        System.out.println(newStudent.getName());

        studentTable.getItems().add(newStudent);

    }

    @FXML
    void handleDeleteButton(ActionEvent event) {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        studentTable.getItems().remove(selectedStudent);

    }

    @FXML
    void handleRegisterButton(ActionEvent event) {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            System.out.println("No student selected.");
            return;
        }
        System.out.println("Selected student: " + selectedStudent.getName());
        GUI.instance.setRoot("StudentRegisterScreen.fxml");
    }
    

    @FXML
    void handleUpdateButton(ActionEvent event) {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        selectedStudent.setName(nameTEXT.getText());
        selectedStudent.setEmail(emailTEXT.getText());
        selectedStudent.setBirthdate(birthdateTEXT.getText());
        selectedStudent.setGender(genderTEXT.getText());
        selectedStudent.setPostcode(postcodeTEXT.getText());
        selectedStudent.setCity(cityTEXT.getText());
        selectedStudent.setCountry(countryTEXT.getText());
        studentTable.refresh();

    }

}
