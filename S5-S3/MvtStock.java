package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MvtStock {
    private String idMvtStock;
    private String dateMouvement;
    private int entree;
    private int sortie;
    private String typeMvt;
    private String idAchat;
    private String idReparationDetails;
    private String idComposant;

    public MvtStock() {
    }

    public MvtStock(String idMvtStock, String dateMouvement, int entree, int sortie, String typeMvt, 
                    String idAchat, String idReparationDetails, String idComposant) {
        this.idMvtStock = idMvtStock;
        this.dateMouvement = dateMouvement;
        this.entree = entree;
        this.sortie = sortie;
        this.typeMvt = typeMvt;
        this.idAchat = idAchat;
        this.idReparationDetails = idReparationDetails;
        this.idComposant = idComposant;
    }

    public String getIdMvtStock() {
        return idMvtStock;
    }

    public void setIdMvtStock(String idMvtStock) {
        this.idMvtStock = idMvtStock;
    }

    public String getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(String dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public int getEntree() {
        return entree;
    }

    public void setEntree(int entree) {
        this.entree = entree;
    }

    public int getSortie() {
        return sortie;
    }

    public void setSortie(int sortie) {
        this.sortie = sortie;
    }

    public String getTypeMvt() {
        return typeMvt;
    }

    public void setTypeMvt(String typeMvt) {
        this.typeMvt = typeMvt;
    }

    public String getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(String idAchat) {
        this.idAchat = idAchat;
    }

    public String getIdReparationDetails() {
        return idReparationDetails;
    }

    public void setIdReparationDetails(String idReparationDetails) {
        this.idReparationDetails = idReparationDetails;
    }

    public String getIdComposant() {
        return idComposant;
    }

    public void setIdComposant(String idComposant) {
        this.idComposant = idComposant;
    }

    // Méthode pour récupérer tous les mouvements de stock
    public static List<MvtStock> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM MvtStock";
        List<MvtStock> mvtStockList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                mvtStockList.add(new MvtStock(
                    rs.getString("id_mvt_stock"),
                    rs.getString("date_mouvement"),
                    rs.getInt("entree"),
                    rs.getInt("sortie"),
                    rs.getString("type_mvt"),
                    rs.getString("id_achat"),
                    rs.getString("id_reparation_details"),
                    rs.getString("id_composant")
                ));
            }
        }
        return mvtStockList;
    }

    // Méthode pour récupérer un mouvement de stock par ID
    public static MvtStock getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM MvtStock WHERE id_mvt_stock = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new MvtStock(
                        rs.getString("id_mvt_stock"),
                        rs.getString("date_mouvement"),
                        rs.getInt("entree"),
                        rs.getInt("sortie"),
                        rs.getString("type_mvt"),
                        rs.getString("id_achat"),
                        rs.getString("id_reparation_details"),
                        rs.getString("id_composant")
                    );
                }
            }
        }
        return null;
    }

    // Méthode pour insérer un mouvement de stock
    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO MvtStock (id_mvt_stock, date_mouvement, entree, sortie, type_mvt, " +
                       "id_achat, id_reparation_details, id_composant) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idMvtStock);
            pstmt.setString(2, this.dateMouvement);
            pstmt.setInt(3, this.entree);
            pstmt.setInt(4, this.sortie);
            pstmt.setString(5, this.typeMvt);
            pstmt.setString(6, this.idAchat);
            pstmt.setString(7, this.idReparationDetails);
            pstmt.setString(8, this.idComposant);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour mettre à jour un mouvement de stock
    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE MvtStock SET date_mouvement = ?, entree = ?, sortie = ?, type_mvt = ?, " +
                       "id_achat = ?, id_reparation_details = ?, id_composant = ? WHERE id_mvt_stock = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.dateMouvement);
            pstmt.setInt(2, this.entree);
            pstmt.setInt(3, this.sortie);
            pstmt.setString(4, this.typeMvt);
            pstmt.setString(5, this.idAchat);
            pstmt.setString(6, this.idReparationDetails);
            pstmt.setString(7, this.idComposant);
            pstmt.setString(8, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour supprimer un mouvement de stock
    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM MvtStock WHERE id_mvt_stock = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
