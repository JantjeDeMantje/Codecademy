package com.codecademy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import com.codecademy.controllers.StudentScreenController;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StudentScreenControllerTest {

    private StudentScreenController studentScreenController;

    @BeforeEach
    public void setUp() {
        studentScreenController = new StudentScreenController();
    }

    @AfterEach
    public void tearDown() {
        studentScreenController = null;
    }

    @Test
    public void testIsValidEmailShouldReturnTrue() {
        assertTrue(studentScreenController.isValidEmail("test@example.com"));
    }

    @Test
    public void testIsValidEmailShouldReturnFalse() {
        assertFalse(studentScreenController.isValidEmail("invalid-email"));
    }

    @Test
    public void testIsValidZipcodeShouldReturnTrue() {
        assertTrue(studentScreenController.isValidZipcode("1234 AB"));
    }

    @Test
    public void testIsValidZipcodeShouldReturnFalse() {
        assertFalse(studentScreenController.isValidZipcode("1234AB"));
    }

    @Test
    public void testIsValidBirthdateShouldReturnTrue() {
        assertTrue(studentScreenController.isValidBirthdate("01-01-2000"));
    }

    @Test
    public void testIsValidBirthdateShouldReturnFalse() {
        assertFalse(studentScreenController.isValidBirthdate("32-01-2000"));
    }

}