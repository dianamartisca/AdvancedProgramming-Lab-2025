package org.example.dao;

import org.example.dto.ContinentDTO;
import org.example.util.DatabaseConnection;

import java.sql.*;

public class Continent {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public void create(String name) throws SQLException {
        String sql = "insert into continents (name) values (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }

    public ContinentDTO findByName(String name) throws SQLException {
        String sql = "select * from continents where name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ContinentDTO(rs.getInt("id"), rs.getString("name"));
            }
        }
        return null;
    }

    public ContinentDTO findById(int id) throws SQLException {
        String sql = "select * from continents where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ContinentDTO(rs.getInt("id"), rs.getString("name"));
            }
        }
        return null;
    }
}