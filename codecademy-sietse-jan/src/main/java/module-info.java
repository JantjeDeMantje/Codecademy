module com.codecademy {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.codecademy.controllers to javafx.fxml;

    exports com.codecademy;
    exports com.codecademy.controllers;
    exports com.codecademy.domain;
    exports com.codecademy.logic;
}
