package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        DatabaseSetup.initializeDatabase();
        DatabaseSetup.importData();
        getDistanceBetweenRandomCities();
        getDistanceBetweenRandomCities();
        getDistanceBetweenRandomCities();
    }

    public static void getDistanceBetweenRandomCities() {
        try (Connection connection = DatabaseConnection.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            String query = "select id, country, name, capital, latitude, longitude from cities order by random() limit 2";
            ResultSet resultSet = statement.executeQuery(query);

            City city1 = null;
            City city2 = null;

            if (resultSet.next()) {
                city1 = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("country"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("capital"),
                        resultSet.getDouble("latitude"),
                        resultSet.getDouble("longitude")
                );
            }

            if (resultSet.next()) {
                city2 = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("country"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("capital"),
                        resultSet.getDouble("latitude"),
                        resultSet.getDouble("longitude")
                );
            }

            if (city1 != null && city2 != null) {
                double distance = city1.calculateDistance(city2);
                System.out.println("Distance between " + city1.getName() + " and " + city2.getName() + ": " + distance + " km");
            } else {
                System.out.println("Not enough cities in the database to calculate distance.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}