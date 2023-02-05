package com.hspjava.modele;

public class Produit {
    public int id_produit;
    public String libelle;
    public String description;
    public int niv_dangerosite;
    public int stock;
    public int ref_gestionnaire_de_stock;

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNiv_dangerosite() {
        return niv_dangerosite;
    }

    public void setNiv_dangerosite(int niv_dangerosite) {
        this.niv_dangerosite = niv_dangerosite;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getRef_gestionnaire_de_stock() {
        return ref_gestionnaire_de_stock;
    }

    public void setRef_gestionnaire_de_stock(int ref_gestionnaire_de_stock) {
        this.ref_gestionnaire_de_stock = ref_gestionnaire_de_stock;
    }
}
