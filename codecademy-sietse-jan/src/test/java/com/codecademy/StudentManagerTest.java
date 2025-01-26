package com.codecademy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import com.codecademy.logic.StudentManager;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StudentManagerTest {

    private StudentManager studentManager;

    @BeforeEach
    public void setUp() {
        studentManager = new StudentManager();
    }

    @AfterEach
    public void tearDown() {
        studentManager = null;
    }

    @Test
    public void testIsUniqueEmailShouldReturnTrue() {
        assertTrue(studentManager.checkUniqueEmail("unique@example.com", 1));
    }

    @Test
    public void testConvertStringToDateShouldReturnTrue() {
        assertTrue(studentManager.convertStringToDate("1990-01-01") instanceof java.sql.Date);
    }

    @Test
    public void testConvertStringToDateShouldReturnFalse() {
        assertFalse(studentManager.convertStringToDate("1990-01-ab") instanceof java.sql.Date);
    }
}