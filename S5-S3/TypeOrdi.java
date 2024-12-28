package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeOrdi {
    private String idTypeOrdi;
    private String val;

    // Constructeurs
    public TypeOrdi() { }

    public TypeOrdi(String idTypeOrdi, String val) {
        this.idTypeOrdi = idTypeOrdi;
        this.val = val;
    }

    // Getters et Setters
    public String getIdTypeOrdi() {
        return idTypeOrdi;
    }

    public void setIdTypeOrdi(String idTypeOrdi) {
        this.idTypeOrdi = idTypeOrdi;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    // Méthodes CRUD
    public static List<TypeOrdi> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM TypeOrdi";
        List<TypeOrdi> typeOrdis = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                typeOrdis.add(new TypeOrdi(
                    rs.getString("id_type_ordi"),
                    rs.getString("val")
                ));
            }
        }
        return typeOrdis;
    }

    public static TypeOrdi getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM TypeOrdi WHERE id_type_ordi = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new TypeOrdi(
                        rs.getString("id_type_ordi"),
                        rs.getString("val")
                    );
                }
            }
        }
        return null;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO TypeOrdi (id_type_ordi, val) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idTypeOrdi);
            pstmt.setString(2, this.val);
            pstmt.executeUpdate();
        }
    }

    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE TypeOrdi SET val = ? WHERE id_type_ordi = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.val);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM TypeOrdi WHERE id_type_ordi = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
