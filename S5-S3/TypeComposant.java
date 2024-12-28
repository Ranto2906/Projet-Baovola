package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeComposant {
    private String idTypeComposant;
    private String val;
    private String description;

    // Constructeurs
    public TypeComposant() { }

    public TypeComposant(String idTypeComposant, String val, String description) {
        this.idTypeComposant = idTypeComposant;
        this.val = val;
        this.description = description;
    }

    // Getters et Setters
    public String getIdTypeComposant() {
        return idTypeComposant;
    }

    public void setIdTypeComposant(String idTypeComposant) {
        this.idTypeComposant = idTypeComposant;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Méthodes CRUD
    public static List<TypeComposant> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM TypeComposant";
        List<TypeComposant> typeComposants = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                typeComposants.add(new TypeComposant(
                    rs.getString("id_type_composant"),
                    rs.getString("val"),
                    rs.getString("description")
                ));
            }
        }
        return typeComposants;
    }

    public static TypeComposant getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM TypeComposant WHERE id_type_composant = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TypeComposant(
                        rs.getString("id_type_composant"),
                        rs.getString("val"),
                        rs.getString("description")
                    );
                }
            }
        }
        return null;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO TypeComposant (id_type_composant, val, description) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idTypeComposant);
            pstmt.setString(2, this.val);
            pstmt.setString(3, this.description);
            pstmt.executeUpdate();
        }
    }

    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE TypeComposant SET val = ?, description = ? WHERE id_type_composant = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.val);
            pstmt.setString(2, this.description);
            pstmt.setString(3, id);
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM TypeComposant WHERE id_type_composant = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
