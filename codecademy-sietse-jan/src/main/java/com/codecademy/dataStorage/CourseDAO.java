package com.codecademy.dataStorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.codecademy.domain.Course;
import com.codecademy.domain.Difficulty;

public class CourseDAO {

    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT CourseName, Subject, IntroductionText, Difficulty\r\n" + //
                                        "FROM Course";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String courseName = resultSet.getString("CourseName");
                    String subject = resultSet.getString("Subject");
                    String introductionText = resultSet.getString("IntroductionText");
                    Difficulty difficulty = Difficulty.valueOf(resultSet.getString("Difficulty"));
                    courses.add(new Course(courseName, subject, introductionText, difficulty));
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return courses;
    }

    public Course getCourseInfo(String courseName) {
        Course course = null;
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT CourseName, Subject, IntroductionText, Difficulty\r\n" + //
                                        "FROM Course\r\n" + //
                                        "WHERE CourseName = '" + courseName + "'";
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    String subject = resultSet.getString("Subject");
                    String introductionText = resultSet.getString("IntroductionText");
                    Difficulty difficulty = Difficulty.valueOf(resultSet.getString("Difficulty"));
                    course = new Course(courseName, subject, introductionText, difficulty);
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        }
        return course;
    }
}
