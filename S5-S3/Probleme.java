package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Probleme {
    private String idProbleme;
    private String description;
    private String idComposant;
    private String idTypeProbleme;

    public Probleme() {
    }

    public Probleme(String idProbleme, String description, String idComposant, String idTypeProbleme) {
        this.idProbleme = idProbleme;
        this.description = description;
        this.idComposant = idComposant;
        this.idTypeProbleme = idTypeProbleme;
    }

    public String getIdProbleme() {
        return idProbleme;
    }

    public void setIdProbleme(String idProbleme) {
        this.idProbleme = idProbleme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdComposant() {
        return idComposant;
    }

    public void setIdComposant(String idComposant) {
        this.idComposant = idComposant;
    }

    public String getIdTypeProbleme() {
        return idTypeProbleme;
    }

    public void setIdTypeProbleme(String idTypeProbleme) {
        this.idTypeProbleme = idTypeProbleme;
    }

    // Méthode pour récupérer tous les problèmes
    public static List<Probleme> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Probleme";
        List<Probleme> problemes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                problemes.add(new Probleme(
                    rs.getString("id_probleme"),
                    rs.getString("description"),
                    rs.getString("id_composant"),
                    rs.getString("id_type_probleme")
                ));
            }
        }
        return problemes;
    }

    // Méthode pour récupérer un problème par son ID
    public static Probleme getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM Probleme WHERE id_probleme = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Probleme(
                        rs.getString("id_probleme"),
                        rs.getString("description"),
                        rs.getString("id_composant"),
                        rs.getString("id_type_probleme")
                    );
                }
            }
        }
        return null;
    }

    // Méthode pour insérer un nouveau problème
    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Probleme (id_probleme, description, id_composant, id_type_probleme) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idProbleme);
            pstmt.setString(2, this.description);
            pstmt.setString(3, this.idComposant);
            pstmt.setString(4, this.idTypeProbleme);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour mettre à jour un problème existant
    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE Probleme SET description = ?, id_composant = ?, id_type_probleme = ? WHERE id_probleme = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.description);
            pstmt.setString(2, this.idComposant);
            pstmt.setString(3, this.idTypeProbleme);
            pstmt.setString(4, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour supprimer un problème
    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM Probleme WHERE id_probleme = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
