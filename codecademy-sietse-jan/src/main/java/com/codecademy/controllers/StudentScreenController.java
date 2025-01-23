package com.codecademy.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private TextField birthdateDayTEXT;

    @FXML
    private TextField birthdateMonthTEXT;

    @FXML
    private TextField birthdateYearTEXT;

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
        setBirthdateText();
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
        if (!isValidEmail(emailTEXT.getText())) {
            System.out.println("Invalid email format");
            return;
        }

        if (!isValidZipcode(zipcodeTEXT.getText())) {
            System.out.println("Invalid zipcode format");
            return;
        }

        Student newStudent = studentManager.createStudent(
                nameTEXT.getText(), emailTEXT.getText(),
                getDate(), genderTEXT.getText(), addressTEXT.getText(),
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
        if (!isValidEmail(emailTEXT.getText())) {
            System.out.println("Invalid email format");
            showAlert("Ongeldig Email", "Deze email is niet geldig. Voer een geldig emailadres in.");
            return;
        }

        if (!isValidZipcode(zipcodeTEXT.getText())) {
            System.out.println("Invalid zipcode format");
        if (!isValidBirthdate(getDate())){
            System.out.println("Invalid birthdate format");
            showAlert("Ongeldige Geboortedatum", "Deze geboortedatum is niet geldig. Voer een geldige geboortedatum in. (dd-mm-yyyy)");
            return;
        }

        studentManager.updateStudent(
                selectedStudent, nameTEXT.getText(), emailTEXT.getText(),
                createDate(birthdateDayTEXT.getText(), birthdateMonthTEXT.getText(), birthdateYearTEXT.getText()), genderTEXT.getText(), addressTEXT.getText(),
                zipcodeTEXT.getText(), cityTEXT.getText(),
                countryTEXT.getText(), studentTable);

        // Logs the name of the updated student
        System.out.println("UpdateStudentButton pressed: " + selectedStudent.getName());
    }

    private boolean isValidEmail(String email) { // This method checks if the email is valid.
        String emailRegex = "^[a-zA-Z]+(\\.[a-zA-Z]+)*@[a-zA-Z]+\\.[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidZipcode(String zipcode) { // This method checks if the zipcode is valid.
        String zipcodeRegex = "^[1-9][0-9]{3} [A-Z]{2}$";
        Pattern pattern = Pattern.compile(zipcodeRegex);
        Matcher matcher = pattern.matcher(zipcode);
        return matcher.matches();
    }

    private boolean isValidBirthdate(String birthdate) { // This method checks if the birthdate is valid.
        String birthdateRegex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$";
        Pattern pattern = Pattern.compile(birthdateRegex);
        Matcher matcher = pattern.matcher(birthdate);
        
        if (!matcher.matches()) {
            return false;
        }
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            LocalDate date = LocalDate.parse(birthdate, formatter);
            
            // Check if the date is valid like 30-02 or 31-04 etc.
            if (date.getDayOfMonth() != Integer.parseInt(birthdate.substring(0, 2))) {
                return false;
            }
    
            return !date.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    private void setBirthdateText(){ // This method sets the birthdate text.
        String[] date = selectedStudent.getBirthdate().split("-");
        birthdateDayTEXT.setText(date[0]);
        birthdateMonthTEXT.setText(date[1]);
        birthdateYearTEXT.setText(date[2]);
    }

    private String createDate(String day, String month, String year){ // This method creates a date (from 3 textfields).
        String date = day + "-" + month + "-" + year;
        System.out.println(date);
        return date;
    }

    private void showAlert(String title, String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
