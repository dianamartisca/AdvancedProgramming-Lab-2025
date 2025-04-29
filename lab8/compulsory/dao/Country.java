package org.example.dao;

import org.example.dto.CountryDTO;
import org.example.util.DatabaseConnection;

import java.sql.*;

public class Country {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    public void create(String name, String code, int continentId) throws SQLException {
        String sql = "insert into countries (name, code, continent_id) values (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, code);
            stmt.setInt(3, continentId);
            stmt.executeUpdate();
        }
    }

    public CountryDTO findByName(String name) throws SQLException {
        String sql = "select * from countries where name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CountryDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("code"),
                        rs.getInt("continent_id")
                );
            }
        }
        return null;
    }

    public CountryDTO findById(int id) throws SQLException {
        String sql = "select * from countries where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CountryDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("code"),
                        rs.getInt("continent_id")
                );
            }
        }
        return null;
    }
}