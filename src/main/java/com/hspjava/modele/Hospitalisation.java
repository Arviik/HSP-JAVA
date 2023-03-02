package com.hspjava.modele;

import java.util.Date;

public class Hospitalisation extends Table {
    private Date date;
    private String description_maladie;
    private int est_termine;
    private int ref_dossier;
    private int ref_chambre;

    public Hospitalisation(int id, Date date, String description_maladie, int est_termine, int ref_dossier, int ref_chambre) {
        super(id);
        this.date = date;
        this.description_maladie = description_maladie;
        this.est_termine = est_termine;
        this.ref_dossier = ref_dossier;
        this.ref_chambre = ref_chambre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription_maladie() {
        return description_maladie;
    }

    public void setDescription_maladie(String description_maladie) {
        this.description_maladie = description_maladie;
    }

    public int getEst_termine() {
        return est_termine;
    }

    public void setEst_termine(int est_termine) {
        this.est_termine = est_termine;
    }

    public int getRef_dossier() {
        return ref_dossier;
    }

    public void setRef_dossier(int ref_dossier) {
        this.ref_dossier = ref_dossier;
    }

    public int getRef_chambre() {
        return ref_chambre;
    }

    public void setRef_chambre(int ref_chambre) {
        this.ref_chambre = ref_chambre;
    }
}
