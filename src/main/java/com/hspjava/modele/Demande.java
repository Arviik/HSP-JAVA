package com.hspjava.modele;

public class Demande extends Table {
    private String raison;
    private boolean est_valide;
    private int ref_gestionnaire_de_stock;
    private int ref_medecin;

    public Demande(String raison,int ref_medecin) {
        this.raison = raison;
        this.ref_medecin = ref_medecin;
    }
    public Demande(int id, String raison, boolean est_valide, int ref_gestionnaire_de_stock, int ref_medecin) {
        super(id);
        this.raison = raison;
        this.est_valide = est_valide;
        this.ref_gestionnaire_de_stock = ref_gestionnaire_de_stock;
        this.ref_medecin = ref_medecin;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public boolean getEst_valide() {
        return est_valide;
    }

    public void setEst_valide(boolean est_valide) {
        this.est_valide = est_valide;
    }

    public int getRef_gestionnaire_de_stock() {
        return ref_gestionnaire_de_stock;
    }

    public void setRef_gestionnaire_de_stock(int ref_gestionnaire_de_stock) {
        this.ref_gestionnaire_de_stock = ref_gestionnaire_de_stock;
    }

    public int getRef_medecin() {
        return ref_medecin;
    }

    public void setRef_medecin(int ref_medecin) {
        this.ref_medecin = ref_medecin;
    }
}
