package com.codecademy.dataStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContentItemDAO {
    
    public ArrayList<Integer> getContentItemIdsByCourse(String courseName) {
        ArrayList<Integer> contentItemIds = new ArrayList<>();
        String query = "SELECT ContentItemId FROM ContentItem WHERE CourseName = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                contentItemIds.add(resultSet.getInt("ContentItemId"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contentItemIds;
    }

    public Map<Integer, String> getModulesTitlesMap(ArrayList<Integer> contentItemIds) {
        Map<Integer, String> moduleTitlesMap = new HashMap<>();
        String query = "SELECT ContentItemId, Title FROM ContentItem JOIN Module ON ContentItemId = ModuleId WHERE ContentItemId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (Integer contentItemId : contentItemIds) {
                preparedStatement.setInt(1, contentItemId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    moduleTitlesMap.put(resultSet.getInt("ContentItemId"), resultSet.getString("Title"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return moduleTitlesMap;
    }

    public Map<Integer, String> getWebcastTitlesMap(ArrayList<Integer> contentItemIds) {
        Map<Integer, String> webcastTitlesMap = new HashMap<>();
        String query = "SELECT ContentItemId, Title FROM ContentItem JOIN Webcast ON ContentItemId = WebcastId WHERE ContentItemId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (Integer contentItemId : contentItemIds) {
                preparedStatement.setInt(1, contentItemId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    webcastTitlesMap.put(resultSet.getInt("ContentItemId"), resultSet.getString("Title"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return webcastTitlesMap;
    }
}
