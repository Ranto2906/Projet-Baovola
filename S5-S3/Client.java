package org.projets3s5.atelier.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private String idClient;
    private String nom;
    private String telephone;
    private String email;
    private String dateInscription;

    public Client() {
    }

    public Client(String idClient, String nom, String telephone, String email, String dateInscription) {
        this.idClient = idClient;
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
        this.dateInscription = dateInscription;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
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

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public static List<Client> getAll(Connection connection) throws SQLException {
        String query = "SELECT * FROM Client";
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                clients.add(new Client(
                        rs.getString("id_client"),
                        rs.getString("nom"),
                        rs.getString("telephone"),
                        rs.getString("email"),
                        rs.getString("date_inscription")
                ));
            }
        }
        return clients;
    }

    public static Client getById(Connection connection, String id) throws SQLException {
        String query = "SELECT * FROM Client WHERE id_client = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                            rs.getString("id_client"),
                            rs.getString("nom"),
                            rs.getString("telephone"),
                            rs.getString("email"),
                            rs.getString("date_inscription")
                    );
                }
            }
        }
        return null;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO Client (id_client, nom, telephone, email, date_inscription) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.idClient);
            pstmt.setString(2, this.nom);
            pstmt.setString(3, this.telephone);
            pstmt.setString(4, this.email);
            pstmt.setString(5, this.dateInscription);
            pstmt.executeUpdate();
        }
    }

    public void update(Connection connection, String id) throws SQLException {
        String query = "UPDATE Client SET nom = ?, telephone = ?, email = ?, date_inscription = ? WHERE id_client = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, this.nom);
            pstmt.setString(2, this.telephone);
            pstmt.setString(3, this.email);
            pstmt.setString(4, this.dateInscription);
            pstmt.setString(5, id);
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection connection, String id) throws SQLException {
        String query = "DELETE FROM Client WHERE id_client = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
    }
}
