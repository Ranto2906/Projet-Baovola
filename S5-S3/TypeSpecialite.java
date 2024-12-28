package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeSpecialite {
    private String idTypeSpecialite;
    private String val;

    // Constructeurs
    public TypeSpecialite() { }

    public TypeSpecialite(String idTypeSpecialite, String val) {
        this.idTypeSpecialite = idTypeSpecialite;
        this.val = val;
    }

    // Getters et Setters
    public String getIdTypeSpecialite() {
        return idTypeSpecialite;
    }

    public void setIdTypeSpecialite(String idTypeSpecialite) {
        this.idTypeSpecialite = idTypeSpecialite;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    // Méthodes CRUD
    public static List<TypeSpecialite> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM TypeSpecialite";
        List<TypeSpecialite> typeSpecialites = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                typeSpecialites.add(new TypeSpecialite(
                    rs.getString("id_type_specialite"),
                    rs.getString("val")
                ));
            }
        }
        return typeSpecialites;
    }

    public static TypeSpecialite getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM TypeSpecialite WHERE id_type_specialite = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TypeSpecialite(
                        rs.getString("id_type_specialite"),
                        rs.getString("val")
                    );
                }
            }
        }
        return null;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO TypeSpecialite (id_type_specialite, val) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idTypeSpecialite);
            pstmt.setString(2, this.val);
            pstmt.executeUpdate();
        }
    }

    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE TypeSpecialite SET val = ? WHERE id_type_specialite = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.val);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM TypeSpecialite WHERE id_type_specialite = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
