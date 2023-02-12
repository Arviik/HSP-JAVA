package com.hspjava.modele;

import com.hspjava.database.Table;

public class Fournisseur extends Table {
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
