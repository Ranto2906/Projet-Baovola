package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Fournisseur {
    private String idFournisseur;
    private String val;

    // Constructeurs
    public Fournisseur() { }

    public Fournisseur(String idFournisseur, String val) {
        this.idFournisseur = idFournisseur;
        this.val = val;
    }

    // Getters et Setters
    public String getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(String idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    // Méthodes CRUD
    public static List<Fournisseur> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Fournisseur";
        List<Fournisseur> fournisseurs = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                fournisseurs.add(new Fournisseur(
                    rs.getString("id_fournisseur"),
                    rs.getString("val")
                ));
            }
        }
        return fournisseurs;
    }

    public static Fournisseur getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM Fournisseur WHERE id_fournisseur = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Fournisseur(
                        rs.getString("id_fournisseur"),
                        rs.getString("val")
                    );
                }
            }
        }
        return null;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Fournisseur (id_fournisseur, val) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idFournisseur);
            pstmt.setString(2, this.val);
            pstmt.executeUpdate();
        }
    }

    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE Fournisseur SET val = ? WHERE id_fournisseur = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.val);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM Fournisseur WHERE id_fournisseur = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
