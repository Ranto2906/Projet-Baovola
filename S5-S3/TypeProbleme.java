package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeProbleme {
    private String idTypeProbleme;
    private String val;

    // Constructeurs
    public TypeProbleme() { }

    public TypeProbleme(String idTypeProbleme, String val) {
        this.idTypeProbleme = idTypeProbleme;
        this.val = val;
    }

    // Getters et Setters
    public String getIdTypeProbleme() {
        return idTypeProbleme;
    }

    public void setIdTypeProbleme(String idTypeProbleme) {
        this.idTypeProbleme = idTypeProbleme;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    // Méthodes CRUD
    public static List<TypeProbleme> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM TypeProbleme";
        List<TypeProbleme> typeProblemes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                typeProblemes.add(new TypeProbleme(
                    rs.getString("id_type_probleme"),
                    rs.getString("val")
                ));
            }
        }
        return typeProblemes;
    }

    public static TypeProbleme getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM TypeProbleme WHERE id_type_probleme = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TypeProbleme(
                        rs.getString("id_type_probleme"),
                        rs.getString("val")
                    );
                }
            }
        }
        return null;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO TypeProbleme (id_type_probleme, val) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idTypeProbleme);
            pstmt.setString(2, this.val);
            pstmt.executeUpdate();
        }
    }

    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE TypeProbleme SET val = ? WHERE id_type_probleme = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.val);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM TypeProbleme WHERE id_type_probleme = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
