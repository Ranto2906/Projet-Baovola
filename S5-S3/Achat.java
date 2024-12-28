package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Achat {
    private String idAchat;
    private String dateAchat;
    private int quantite;
    private double prixUnitaire;
    private String idFournisseur;
    private String idComposant;

    public Achat() {
    }

    public Achat(String idAchat, String dateAchat, int quantite, double prixUnitaire, String idFournisseur, String idComposant) {
        this.idAchat = idAchat;
        this.dateAchat = dateAchat;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.idFournisseur = idFournisseur;
        this.idComposant = idComposant;
    }

    public String getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(String idAchat) {
        this.idAchat = idAchat;
    }

    public String getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(String dateAchat) {
        this.dateAchat = dateAchat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(String idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public String getIdComposant() {
        return idComposant;
    }

    public void setIdComposant(String idComposant) {
        this.idComposant = idComposant;
    }

    // Méthode pour récupérer tous les achats
    public static List<Achat> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Achat";
        List<Achat> achats = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                achats.add(new Achat(
                    rs.getString("id_achat"),
                    rs.getString("date_achat"),
                    rs.getInt("quantite"),
                    rs.getDouble("prix_unitaire"),
                    rs.getString("id_fournisseur"),
                    rs.getString("id_composant")
                ));
            }
        }
        return achats;
    }

    // Méthode pour récupérer un achat par ID
    public static Achat getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM Achat WHERE id_achat = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Achat(
                        rs.getString("id_achat"),
                        rs.getString("date_achat"),
                        rs.getInt("quantite"),
                        rs.getDouble("prix_unitaire"),
                        rs.getString("id_fournisseur"),
                        rs.getString("id_composant")
                    );
                }
            }
        }
        return null;
    }

    // Méthode pour insérer un nouvel achat
    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Achat (id_achat, date_achat, quantite, prix_unitaire, id_fournisseur, id_composant) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idAchat);
            pstmt.setString(2, this.dateAchat);
            pstmt.setInt(3, this.quantite);
            pstmt.setDouble(4, this.prixUnitaire);
            pstmt.setString(5, this.idFournisseur);
            pstmt.setString(6, this.idComposant);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour mettre à jour un achat
    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE Achat SET date_achat = ?, quantite = ?, prix_unitaire = ?, id_fournisseur = ?, id_composant = ? " +
                       "WHERE id_achat = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.dateAchat);
            pstmt.setInt(2, this.quantite);
            pstmt.setDouble(3, this.prixUnitaire);
            pstmt.setString(4, this.idFournisseur);
            pstmt.setString(5, this.idComposant);
            pstmt.setString(6, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour supprimer un achat
    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM Achat WHERE id_achat = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
