package com.hspjava.modele;

public class Concerne {
    private int quantite;
    private int ref_demande;
    private int ref_produit;

    public Concerne(int quantite, int ref_demande, int ref_produit){
        this.quantite= quantite;
        this.ref_demande= ref_demande;
        this.ref_produit= ref_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getRef_demande() {
        return ref_demande;
    }

    public void setRef_demande(int ref_demande) {
        this.ref_demande = ref_demande;
    }

    public int getRef_produit() {
        return ref_produit;
    }

    public void setRef_produit(int ref_produit) {
        this.ref_produit = ref_produit;
    }
}
