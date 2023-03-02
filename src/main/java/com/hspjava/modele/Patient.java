package com.hspjava.modele;

public class Patient extends Table {
    private String nom;
    private String prenom;
    private int num_securite_sociale;
    private String email;
    private int telephone;
    private String adresse;
    private int ref_secretaire;

    public Patient(String nom, String prenom, int num_securite_sociale, String email, int telephone, String adresse, int ref_secretaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_securite_sociale = num_securite_sociale;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.ref_secretaire = ref_secretaire;
    }

    public Patient(int id, String nom, String prenom, int num_securite_sociale, String email, int telephone, String adresse, int ref_secretaire) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.num_securite_sociale = num_securite_sociale;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.ref_secretaire = ref_secretaire;
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
