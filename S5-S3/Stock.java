package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    private String idStock;
    private int quantiteDisponible;
    private String idComposant;

    public Stock() {
    }

    public Stock(String idStock, int quantiteDisponible, String idComposant) {
        this.idStock = idStock;
        this.quantiteDisponible = quantiteDisponible;
        this.idComposant = idComposant;
    }

    public String getIdStock() {
        return idStock;
    }

    public void setIdStock(String idStock) {
        this.idStock = idStock;
    }

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

    public String getIdComposant() {
        return idComposant;
    }

    public void setIdComposant(String idComposant) {
        this.idComposant = idComposant;
    }

    // Méthode pour récupérer tous les stocks
    public static List<Stock> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Stock";
        List<Stock> stocks = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                stocks.add(new Stock(
                    rs.getString("id_stock"),
                    rs.getInt("quantite_disponible"),
                    rs.getString("id_composant")
                ));
            }
        }
        return stocks;
    }

    // Méthode pour récupérer un stock par son ID
    public static Stock getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM Stock WHERE id_stock = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Stock(
                        rs.getString("id_stock"),
                        rs.getInt("quantite_disponible"),
                        rs.getString("id_composant")
                    );
                }
            }
        }
        return null;
    }

    // Méthode pour insérer un nouveau stock
    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Stock (id_stock, quantite_disponible, id_composant) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idStock);
            pstmt.setInt(2, this.quantiteDisponible);
            pstmt.setString(3, this.idComposant);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour mettre à jour un stock existant
    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE Stock SET quantite_disponible = ?, id_composant = ? WHERE id_stock = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, this.quantiteDisponible);
            pstmt.setString(2, this.idComposant);
            pstmt.setString(3, id);
            pstmt.executeUpdate();
        }
    }

    // Méthode pour supprimer un stock
    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM Stock WHERE id_stock = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
