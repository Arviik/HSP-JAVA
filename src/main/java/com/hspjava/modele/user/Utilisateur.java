package com.hspjava.modele.user;

import com.hspjava.services.config.Config;
import com.hspjava.database.Database;
import com.hspjava.modele.Table;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Utilisateur extends Table {
    private String nom;
    private String prenom;
    private String email;
    private String mot_de_passe;

    protected Utilisateur(String tableName) {
        super(tableName);
    }

    public Utilisateur() {
    }

    public Utilisateur(String email, String mot_de_passe) {
        this.email = email;
        this.mot_de_passe = mot_de_passe;
    }

    public Utilisateur(String nom, String prenom, String email, String mot_de_passe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
    }

    protected Utilisateur(String tableName, int id, String nom, String prenom, String email, String mot_de_passe) {
        super(tableName, id);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
    }

    public Utilisateur(int id, String nom, String prenom, String email, String mot_de_passe) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
    }

    public String getUserType() {
        StringBuilder query = new StringBuilder();
        for (String userType : Config.getUserTypes()) {
            query.append("SELECT '").append(userType).append("' AS table_name FROM ").append(userType).append(" WHERE ref_utilisateur = ? UNION ");
        }
        query.delete(query.length() - 7, query.length()).append(";");
        try (PreparedStatement req = Database.getInstance().getCnx().prepareStatement(String.valueOf(query))) {
            for (int i = 1; i < Config.getUserTypes().size() + 1; i++) {
                req.setInt(i, this.getId());
            }
            ResultSet res = req.executeQuery();
            if (res.next()) {
                return res.getString("table_name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public void setUserType(String userType) {
        while (!Objects.equals(getUserType(), "")) {
            try (PreparedStatement req = Database.getInstance().getCnx().prepareStatement("DELETE FROM " + getUserType() + " WHERE " + getUserType() + ".ref_utilisateur = ? ")) {
                req.setInt(1, getId());
                req.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try (PreparedStatement req = Database.getInstance().getCnx().prepareStatement("INSERT INTO " + userType + " (ref_utilisateur) VALUES (?);")) {
            req.setInt(1, getId());
            req.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
}
