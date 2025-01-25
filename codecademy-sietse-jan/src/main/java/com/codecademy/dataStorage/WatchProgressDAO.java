package com.codecademy.dataStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WatchProgressDAO {
    public double getAverageWatchPercentageByStudent(int studentId) {
    double averageWatchPercentage = 0.0;
    String query = "SELECT AVG(WatchPercentage) AS WatchPercentageAVG FROM WatchPercentage WHERE StudentId = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

        preparedStatement.setInt(1, studentId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            averageWatchPercentage = resultSet.getDouble("WatchPercentageAVG");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return averageWatchPercentage;
}
}
    
