package com.hspjava.modele;

public class Produit extends Table {
    private String libelle;
    private String description;
    private int niv_dangerosite;
    private int stock;
    private int ref_gestionnaire_de_stock;

    public Produit(){

    }

    public Produit(String libelle, String description, int niv_dangerosite, int stock, int ref_gestionnaire_de_stock){
        this.libelle = libelle;
        this.description = description;
        this.niv_dangerosite = niv_dangerosite;
        this.stock = stock;
        this.ref_gestionnaire_de_stock = ref_gestionnaire_de_stock;
    }
    public Produit(String libelle, String description, int niv_dangerosite, int stock){
        this.libelle = libelle;
        this.description = description;
        this.niv_dangerosite = niv_dangerosite;
        this.stock = stock;
    }

    public Produit(int id, String libelle, String description, int niv_dangerosite, int stock, int ref_gestionnaire_de_stock) {
        super(id);
        this.libelle = libelle;
        this.description = description;
        this.niv_dangerosite = niv_dangerosite;
        this.stock = stock;
        this.ref_gestionnaire_de_stock = ref_gestionnaire_de_stock;
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

    public int getRef_gestionnaire_stock() {
        return ref_gestionnaire_de_stock;
    }

    public void setRef_gestionnaire_stock(int ref_gestionnaire_de_stock) {
        this.ref_gestionnaire_de_stock = ref_gestionnaire_de_stock;
    }
}
