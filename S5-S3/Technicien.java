package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Technicien {
    private String idTechnicien;
    private String nom;
    private String telephone;
    private String email;
    private String dateEmbauche;
    private String idSalaireTechnicien;
    private String idTypeSpecialite;

    public Technicien() {
    }

    public Technicien(String idTechnicien, String nom, String telephone, String email, String dateEmbauche,
                      String idSalaireTechnicien, String idTypeSpecialite) {
        this.idTechnicien = idTechnicien;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.dateEmbauche = dateEmbauche;
        this.idSalaireTechnicien = idSalaireTechnicien;
        this.idTypeSpecialite = idTypeSpecialite;
    }

    public String getIdTechnicien() {
        return idTechnicien;
    }

    public void setIdTechnicien(String idTechnicien) {
        this.idTechnicien = idTechnicien;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getIdSalaireTechnicien() {
        return idSalaireTechnicien;
    }

    public void setIdSalaireTechnicien(String idSalaireTechnicien) {
        this.idSalaireTechnicien = idSalaireTechnicien;
    }

    public String getIdTypeSpecialite() {
        return idTypeSpecialite;
    }

    public void setIdTypeSpecialite(String idTypeSpecialite) {
        this.idTypeSpecialite = idTypeSpecialite;
    }

    public static List<Technicien> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Technicien";
        List<Technicien> techniciens = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                techniciens.add(new Technicien(
                    rs.getString("id_technicien"),
                    rs.getString("nom"),
                    rs.getString("telephone"),
                    rs.getString("email"),
                    rs.getString("date_embauche"),
                    rs.getString("id_salaire_technicien"),
                    rs.getString("id_type_specialite")
                ));
            }
        }
        return techniciens;
    }

    public static Technicien getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM Technicien WHERE id_technicien = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Technicien(
                        rs.getString("id_technicien"),
                        rs.getString("nom"),
                        rs.getString("telephone"),
                        rs.getString("email"),
                        rs.getString("date_embauche"),
                        rs.getString("id_salaire_technicien"),
                        rs.getString("id_type_specialite")
                    );
                }
            }
        }
        return null;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Technicien (id_technicien, nom, telephone, email, date_embauche, id_salaire_technicien, id_type_specialite) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idTechnicien);
            pstmt.setString(2, this.nom);
            pstmt.setString(3, this.telephone);
            pstmt.setString(4, this.email);
            pstmt.setString(5, this.dateEmbauche);
            pstmt.setString(6, this.idSalaireTechnicien);
            pstmt.setString(7, this.idTypeSpecialite);
            pstmt.executeUpdate();
        }
    }

    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE Technicien SET nom = ?, telephone = ?, email = ?, date_embauche = ?, id_salaire_technicien = ?, id_type_specialite = ? WHERE id_technicien = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.nom);
            pstmt.setString(2, this.telephone);
            pstmt.setString(3, this.email);
            pstmt.setString(4, this.dateEmbauche);
            pstmt.setString(5, this.idSalaireTechnicien);
            pstmt.setString(6, this.idTypeSpecialite);
            pstmt.setString(7, id);
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM Technicien WHERE id_technicien = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
