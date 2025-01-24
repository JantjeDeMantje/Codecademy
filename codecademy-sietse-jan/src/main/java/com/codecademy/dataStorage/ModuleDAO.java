package com.codecademy.dataStorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.codecademy.domain.Module;
import com.codecademy.domain.Course;

public class ModuleDAO {

    public ArrayList<Module> getAllModules(Course course) {
        ArrayList<Module> modules = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT Title\r\n" + //
                               "FROM Module\r\n" + //
                               "WHERE ModuleId IN (\r\n" + //
                               "    SELECT ContentItemId\r\n" + //
                               "    FROM ContentItem\r\n" + //
                               "    WHERE CourseName = '"+course.getCourseName()+"'\r\n" + //
                               ")\r\n" + //
                               "UNION\r\n" + //
                               "SELECT Title\r\n" + //
                               "FROM Webcast\r\n" + //
                               "WHERE WebcastId IN (\r\n" + //
                               "    SELECT ContentItemId\r\n" + //
                               "    FROM ContentItem\r\n" + //
                               "    WHERE CourseName = '"+course.getCourseName()+"'\r\n" + //
                               ");";

                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String title = resultSet.getString("Title");
                    modules.add(new Module(title));
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

        return modules;

    }
    
}
