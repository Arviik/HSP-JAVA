package com.hspjava.modele;

public class Propose {
    public int prix;
    public int ref_fournisseur;
    public int ref_produit;

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getRef_fournisseur() {
        return ref_fournisseur;
    }

    public void setRef_fournisseur(int ref_fournisseur) {
        this.ref_fournisseur = ref_fournisseur;
    }

    public int getRef_produit() {
        return ref_produit;
    }

    public void setRef_produit(int ref_produit) {
        this.ref_produit = ref_produit;
    }
}
