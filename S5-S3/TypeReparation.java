package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeReparation {
    private String idTypeReparation;
    private String val;

    // Constructeurs
    public TypeReparation() { }

    public TypeReparation(String idTypeReparation, String val) {
        this.idTypeReparation = idTypeReparation;
        this.val = val;
    }

    // Getters et Setters
    public String getIdTypeReparation() {
        return idTypeReparation;
    }

    public void setIdTypeReparation(String idTypeReparation) {
        this.idTypeReparation = idTypeReparation;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    // Méthodes CRUD
    public static List<TypeReparation> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM TypeReparation";
        List<TypeReparation> typeReparations = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                typeReparations.add(new TypeReparation(
                    rs.getString("id_type_reparation"),
                    rs.getString("val")
                ));
            }
        }
        return typeReparations;
    }

    public static TypeReparation getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM TypeReparation WHERE id_type_reparation = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TypeReparation(
                        rs.getString("id_type_reparation"),
                        rs.getString("val")
                    );
                }
            }
        }
        return null;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO TypeReparation (id_type_reparation, val) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idTypeReparation);
            pstmt.setString(2, this.val);
            pstmt.executeUpdate();
        }
    }

    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE TypeReparation SET val = ? WHERE id_type_reparation = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.val);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM TypeReparation WHERE id_type_reparation = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
