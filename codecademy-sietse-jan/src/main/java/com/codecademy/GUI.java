package com.codecademy;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application { // This class shows all the GUI elements
    private final String DEFAULT_TITLE = "Codecademy"; // Default title of the application
    public static GUI instance;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application.
    }

    // This method is the startpoint of the JavaFX application.
    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        this.primaryStage = primaryStage; // Set the primary stage.

        setRoot(null); // Starts the first FXML file. If null, it will start the default FXML file.
    }

    // This methode selects and loads the selected FXML file. Nothing selected =
    // homescreen.
    public Parent loadFXML(String fxmlPath) throws Exception {
        if (fxmlPath == null || fxmlPath.isEmpty()) {
            fxmlPath = "/com/codecademy/primary.fxml"; // This is the homescreen.
        } else {
            fxmlPath = "/com/codecademy/" + fxmlPath;
        }
        System.out.println("Loading FXML file: " + fxmlPath); // Logs the FXML file that is loaded.
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath)); // Loads the FXML file from the resources.
        return loader.load();
    }

    // This method sets the root of the JavaFX application.
    public void setRoot(String fxmlPath) {
        try {
            Parent root = loadFXML(fxmlPath); // Load the FXML file.

            Scene scene = new Scene(root, 1200, 700);
            primaryStage.setScene(scene);
            primaryStage.setTitle(DEFAULT_TITLE); // Sets the title.
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
