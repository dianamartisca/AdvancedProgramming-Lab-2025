package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

public class DatabaseSetup {

    public static void initializeDatabase() {
        String createTableSQL = """
            create table if not exists cities (
                id SERIAL PRIMARY KEY,
                country VARCHAR(100),
                name VARCHAR(100),
                capital BOOLEAN,
                latitude DOUBLE PRECISION,
                longitude DOUBLE PRECISION
            );
        """;

        try (Connection connection = DatabaseConnection.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table 'cities' created or already exists.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void importData() {
        String insertSQL = "insert into cities (country, name, capital, latitude, longitude) values (?, ?, ?, ?, ?)";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(DatabaseSetup.class.getResourceAsStream("/concap.csv"))));
             Connection connection = DatabaseConnection.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(insertSQL)) {

            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String country = parts[0];
                String name = parts[1];
                boolean capital = true;
                double latitude = isValidDouble(parts[2]) ? Double.parseDouble(parts[2]) : 0.0;
                double longitude = isValidDouble(parts[3]) ? Double.parseDouble(parts[3]) : 0.0;

                statement.setString(1, country);
                statement.setString(2, name);
                statement.setBoolean(3, capital);
                statement.setDouble(4, latitude);
                statement.setDouble(5, longitude);
                statement.executeUpdate();
            }
            System.out.println("Data imported successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}