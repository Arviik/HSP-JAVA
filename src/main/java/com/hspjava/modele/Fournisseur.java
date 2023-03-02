package com.hspjava.modele;

public class Fournisseur extends Table {
    private String nom;

    public Fournisseur(int id, String nom) {
        super(id);
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
