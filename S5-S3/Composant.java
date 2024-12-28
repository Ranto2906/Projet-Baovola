package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Composant {
    private String idComposant;
    private String nom;
    private double prixAchat;
    private double prixVente;
    private String idTypeComposant;

    public Composant() {
    }

    public Composant(String idComposant, String nom, double prixAchat, double prixVente, String idTypeComposant) {
        this.idComposant = idComposant;
        this.nom = nom;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.idTypeComposant = idTypeComposant;
    }

    public String getIdComposant() {
        return idComposant;
    }

    public void setIdComposant(String idComposant) {
        this.idComposant = idComposant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public String getIdTypeComposant() {
        return idTypeComposant;
    }

    public void setIdTypeComposant(String idTypeComposant) {
        this.idTypeComposant = idTypeComposant;
    }

    // Méthode pour récupérer tous les composants
    public static List<Composant> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Composant";
        List<Composant> composants = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                composants.add(new Composant(
                    rs.getString("id_composant"),
                    rs.getString("nom"),
                    rs.getDouble("prix_achat"),
                    rs.getDouble("prix_vente"),
                    rs.getString("id_type_composant")
                ));
            }
        }
        return composants;
    }

    // Méthode pour récupérer un composant par son ID
    public static Composant getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM Composant WHERE id_composant = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Composant(
                        rs.getString("id_composant"),
                        rs.getString("nom"),
                        rs.getDouble("prix_achat"),
                        rs.getDouble("prix_vente"),
                        rs.getString("id_type_composant")
                    );
                }
            }
        }
        return null;
    }

    // Méthode pour insérer un nouveau composant
    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Composant (id_composant, nom, prix_achat, prix_vente, id_type_composant) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idComposant);
            pstmt.setString(2, this.nom);
            pstmt.setDouble(3, this.prixAchat);
            pstmt.setDouble(4, this.prixVente);
            pstmt.setString(5, this.idTypeComposant);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour mettre à jour un composant existant
    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE Composant SET nom = ?, prix_achat = ?, prix_vente = ?, id_type_composant = ? WHERE id_composant = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.nom);
            pstmt.setDouble(2, this.prixAchat);
            pstmt.setDouble(3, this.prixVente);
            pstmt.setString(4, this.idTypeComposant);
            pstmt.setString(5, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour supprimer un composant
    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM Composant WHERE id_composant = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
