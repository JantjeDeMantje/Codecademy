package com.codecademy.dataStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WatchProgressDAO {

    // This query gets the average watch percentage of a student for a list of content items
    public double getAverageWatchPercentageByStudent(int studentId, ArrayList<Integer> contentItemIds) { 
        double totalWatchPercentage = 0.0;
        int count = 0;
        String query = "SELECT WatchPercentage FROM WatchPercentage WHERE StudentId = ? AND ContentItemId = ?";
    
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    
            for (Integer contentItemId : contentItemIds) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setInt(2, contentItemId);
                ResultSet resultSet = preparedStatement.executeQuery();
    
                if (resultSet.next()) {
                    totalWatchPercentage += resultSet.getDouble("WatchPercentage");
                } else {
                    totalWatchPercentage += 0.0; // If no result, add 0.0
                }
                count++;
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return count > 0 ? totalWatchPercentage / count : 0.0;
    }

    // This query gets the average watch percentage of all students for a content item
    public double getAverageWatchPercentageByContentItem(int contentItemId) {
        double averageWatchPercentage = 0.0;
        String query = "SELECT AVG(WatchPercentage) AS WatchPercentageAVG FROM WatchPercentage WHERE ContentItemId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, contentItemId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                averageWatchPercentage = resultSet.getDouble("WatchPercentageAVG");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return averageWatchPercentage;
    }

    // This query gets the watch percentage of a student for a content item
    public double getWatchPercentageByStudentAndContentItem(int studentId, int contentItemId){
        double watchPercentage = 0.0;
        String query = "SELECT WatchPercentage FROM WatchPercentage WHERE StudentId = ? AND ContentItemId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, contentItemId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                watchPercentage = resultSet.getDouble("WatchPercentage");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return watchPercentage;
    }
}
    
