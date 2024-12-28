package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Systeme {
    private String idSysteme;
    private String val;

    // Constructeurs
    public Systeme() { }

    public Systeme(String idSysteme, String val) {
        this.idSysteme = idSysteme;
        this.val = val;
    }

    // Getters et Setters
    public String getIdSysteme() {
        return idSysteme;
    }

    public void setIdSysteme(String idSysteme) {
        this.idSysteme = idSysteme;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    // Méthodes CRUD
    public static List<Systeme> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Systeme";
        List<Systeme> systemes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                systemes.add(new Systeme(
                    rs.getString("id_systeme"),
                    rs.getString("val")
                ));
            }
        }
        return systemes;
    }

    public static Systeme getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM Systeme WHERE id_systeme = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Systeme(
                        rs.getString("id_systeme"),
                        rs.getString("val")
                    );
                }
            }
        }
        return null;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Systeme (id_systeme, val) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idSysteme);
            pstmt.setString(2, this.val);
            pstmt.executeUpdate();
        }
    }

    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE Systeme SET val = ? WHERE id_systeme = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.val);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM Systeme WHERE id_systeme = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
