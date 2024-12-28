package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaireTechnicien {
    private String idSalaireTechnicien;
    private double salaire;
    private String dateSalaire;

    // Constructeurs
    public SalaireTechnicien() { }

    public SalaireTechnicien(String idSalaireTechnicien, double salaire, String dateSalaire) {
        this.idSalaireTechnicien = idSalaireTechnicien;
        this.salaire = salaire;
        this.dateSalaire = dateSalaire;
    }

    // Getters et Setters
    public String getIdSalaireTechnicien() {
        return idSalaireTechnicien;
    }

    public void setIdSalaireTechnicien(String idSalaireTechnicien) {
        this.idSalaireTechnicien = idSalaireTechnicien;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public String getDateSalaire() {
        return dateSalaire;
    }

    public void setDateSalaire(String dateSalaire) {
        this.dateSalaire = dateSalaire;
    }

    // Méthodes CRUD
    public static List<SalaireTechnicien> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM SalaireTechnicien";
        List<SalaireTechnicien> salaires = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                salaires.add(new SalaireTechnicien(
                    rs.getString("id_salaire_technicien"),
                    rs.getDouble("salaire"),
                    rs.getString("date_salaire")
                ));
            }
        }
        return salaires;
    }

    public static SalaireTechnicien getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM SalaireTechnicien WHERE id_salaire_technicien = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new SalaireTechnicien(
                        rs.getString("id_salaire_technicien"),
                        rs.getDouble("salaire"),
                        rs.getString("date_salaire")
                    );
                }
            }
        }
        return null;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO SalaireTechnicien (id_salaire_technicien, salaire, date_salaire) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idSalaireTechnicien);
            pstmt.setDouble(2, this.salaire);
            pstmt.setString(3, this.dateSalaire);
            pstmt.executeUpdate();
        }
    }

    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE SalaireTechnicien SET salaire = ?, date_salaire = ? WHERE id_salaire_technicien = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setDouble(1, this.salaire);
            pstmt.setString(2, this.dateSalaire);
            pstmt.setString(3, id);
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM SalaireTechnicien WHERE id_salaire_technicien = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
