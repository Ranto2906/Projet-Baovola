package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReparationDetails {
    private String idReparationDetails;
    private double prixReparation;
    private short quantiteUtilisee;
    private String idTypeReparation;
    private String idComposant;
    private String idTechnicien;
    private String idProbleme;
    private String idReparation;

    public ReparationDetails() {
    }

    public ReparationDetails(String idReparationDetails, double prixReparation, short quantiteUtilisee, 
                             String idTypeReparation, String idComposant, String idTechnicien, 
                             String idProbleme, String idReparation) {
        this.idReparationDetails = idReparationDetails;
        this.prixReparation = prixReparation;
        this.quantiteUtilisee = quantiteUtilisee;
        this.idTypeReparation = idTypeReparation;
        this.idComposant = idComposant;
        this.idTechnicien = idTechnicien;
        this.idProbleme = idProbleme;
        this.idReparation = idReparation;
    }

    public String getIdReparationDetails() {
        return idReparationDetails;
    }

    public void setIdReparationDetails(String idReparationDetails) {
        this.idReparationDetails = idReparationDetails;
    }

    public double getPrixReparation() {
        return prixReparation;
    }

    public void setPrixReparation(double prixReparation) {
        this.prixReparation = prixReparation;
    }

    public short getQuantiteUtilisee() {
        return quantiteUtilisee;
    }

    public void setQuantiteUtilisee(short quantiteUtilisee) {
        this.quantiteUtilisee = quantiteUtilisee;
    }

    public String getIdTypeReparation() {
        return idTypeReparation;
    }

    public void setIdTypeReparation(String idTypeReparation) {
        this.idTypeReparation = idTypeReparation;
    }

    public String getIdComposant() {
        return idComposant;
    }

    public void setIdComposant(String idComposant) {
        this.idComposant = idComposant;
    }

    public String getIdTechnicien() {
        return idTechnicien;
    }

    public void setIdTechnicien(String idTechnicien) {
        this.idTechnicien = idTechnicien;
    }

    public String getIdProbleme() {
        return idProbleme;
    }

    public void setIdProbleme(String idProbleme) {
        this.idProbleme = idProbleme;
    }

    public String getIdReparation() {
        return idReparation;
    }

    public void setIdReparation(String idReparation) {
        this.idReparation = idReparation;
    }

    // Méthode pour récupérer tous les détails de réparation
    public static List<ReparationDetails> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM ReparationDetails";
        List<ReparationDetails> reparationDetailsList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                reparationDetailsList.add(new ReparationDetails(
                    rs.getString("id_reparation_details"),
                    rs.getDouble("prix_reparation"),
                    rs.getShort("quantite_utilisee"),
                    rs.getString("id_type_reparation"),
                    rs.getString("id_composant"),
                    rs.getString("id_technicien"),
                    rs.getString("id_probleme"),
                    rs.getString("id_reparation")
                ));
            }
        }
        return reparationDetailsList;
    }

    // Méthode pour récupérer les détails de réparation par ID
    public static ReparationDetails getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM ReparationDetails WHERE id_reparation_details = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new ReparationDetails(
                        rs.getString("id_reparation_details"),
                        rs.getDouble("prix_reparation"),
                        rs.getShort("quantite_utilisee"),
                        rs.getString("id_type_reparation"),
                        rs.getString("id_composant"),
                        rs.getString("id_technicien"),
                        rs.getString("id_probleme"),
                        rs.getString("id_reparation")
                    );
                }
            }
        }
        return null;
    }

    // Méthode pour insérer un nouveau détail de réparation
    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO ReparationDetails (id_reparation_details, prix_reparation, quantite_utilisee, " +
                       "id_type_reparation, id_composant, id_technicien, id_probleme, id_reparation) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idReparationDetails);
            pstmt.setDouble(2, this.prixReparation);
            pstmt.setShort(3, this.quantiteUtilisee);
            pstmt.setString(4, this.idTypeReparation);
            pstmt.setString(5, this.idComposant);
            pstmt.setString(6, this.idTechnicien);
            pstmt.setString(7, this.idProbleme);
            pstmt.setString(8, this.idReparation);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour mettre à jour les détails de réparation
    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE ReparationDetails SET prix_reparation = ?, quantite_utilisee = ?, " +
                       "id_type_reparation = ?, id_composant = ?, id_technicien = ?, id_probleme = ?, " +
                       "id_reparation = ? WHERE id_reparation_details = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, this.prixReparation);
            pstmt.setShort(2, this.quantiteUtilisee);
            pstmt.setString(3, this.idTypeReparation);
            pstmt.setString(4, this.idComposant);
            pstmt.setString(5, this.idTechnicien);
            pstmt.setString(6, this.idProbleme);
            pstmt.setString(7, this.idReparation);
            pstmt.setString(8, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour supprimer un détail de réparation
    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM ReparationDetails WHERE id_reparation_details = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
