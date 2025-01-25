package com.codecademy.dataStorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.codecademy.domain.Webcast;

public class WebcastDAO {

    // This query selects the top 3 webcasts based on the number of views.
    public ArrayList<Webcast> getTop3Webcasts(){
        ArrayList<Webcast> webcasts = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String query = "SELECT TOP 3 Title, Views FROM Webcast ORDER BY Views DESC";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String title = resultSet.getString("Title");
                    int views = resultSet.getInt("Views");
                    webcasts.add(new Webcast(title, views));
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
        return webcasts;
    }
}