package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reparation {
    private String idReparation;
    private String dateReparation;
    private String description;
    private String status;
    private String idOrdinateur;

    public Reparation() {
    }

    public Reparation(String idReparation, String dateReparation, String description, String status, String idOrdinateur) {
        this.idReparation = idReparation;
        this.dateReparation = dateReparation;
        this.description = description;
        this.status = status;
        this.idOrdinateur = idOrdinateur;
    }

    public String getIdReparation() {
        return idReparation;
    }

    public void setIdReparation(String idReparation) {
        this.idReparation = idReparation;
    }

    public String getDateReparation() {
        return dateReparation;
    }

    public void setDateReparation(String dateReparation) {
        this.dateReparation = dateReparation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdOrdinateur() {
        return idOrdinateur;
    }

    public void setIdOrdinateur(String idOrdinateur) {
        this.idOrdinateur = idOrdinateur;
    }

    // Méthode pour récupérer toutes les réparations
    public static List<Reparation> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Reparation";
        List<Reparation> reparations = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                reparations.add(new Reparation(
                    rs.getString("id_reparation"),
                    rs.getString("date_reparation"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getString("id_ordinateur")
                ));
            }
        }
        return reparations;
    }

    // Méthode pour récupérer une réparation par son ID
    public static Reparation getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM Reparation WHERE id_reparation = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Reparation(
                        rs.getString("id_reparation"),
                        rs.getString("date_reparation"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("id_ordinateur")
                    );
                }
            }
        }
        return null;
    }

    // Méthode pour insérer une nouvelle réparation
    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Reparation (id_reparation, date_reparation, description, status, id_ordinateur) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idReparation);
            pstmt.setString(2, this.dateReparation);
            pstmt.setString(3, this.description);
            pstmt.setString(4, this.status);
            pstmt.setString(5, this.idOrdinateur);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour mettre à jour une réparation existante
    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE Reparation SET date_reparation = ?, description = ?, status = ?, id_ordinateur = ? WHERE id_reparation = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.dateReparation);
            pstmt.setString(2, this.description);
            pstmt.setString(3, this.status);
            pstmt.setString(4, this.idOrdinateur);
            pstmt.setString(5, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour supprimer une réparation
    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM Reparation WHERE id_reparation = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
