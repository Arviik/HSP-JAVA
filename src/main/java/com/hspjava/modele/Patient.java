package com.hspjava.modele;

import com.hspjava.database.Table;

public class Patient extends Table {
    private int id_patient;
    private String nom;
    private String prenom;
    private int num_securite_sociale;
    private String email;
    private int telephone;
    private String adresse;
    private int ref_secretaire;

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
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

    public int getNum_securite_sociale() {
        return num_securite_sociale;
    }

    public void setNum_securite_sociale(int num_securite_sociale) {
        this.num_securite_sociale = num_securite_sociale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getRef_secretaire() {
        return ref_secretaire;
    }

    public void setRef_secretaire(int ref_secretaire) {
        this.ref_secretaire = ref_secretaire;
    }
}
