package com.hspjava.modele;

import com.hspjava.database.Table;

import java.util.Date;

public class Dossier extends Table {
    private int id_dossier;
    private Date date;
    private String description_symptome;
    private int niv_gravite;
    private int ref_medecin;
    private int ref_patient;
    private int ref_secretaire;

    public int getId_dossier() {
        return id_dossier;
    }

    public void setId_dossier(int id_dossier) {
        this.id_dossier = id_dossier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription_symptome() {
        return description_symptome;
    }

    public void setDescription_symptome(String description_symptome) {
        this.description_symptome = description_symptome;
    }

    public int getNiv_gravite() {
        return niv_gravite;
    }

    public void setNiv_gravite(int niv_gravite) {
        this.niv_gravite = niv_gravite;
    }

    public int getRef_medecin() {
        return ref_medecin;
    }

    public void setRef_medecin(int ref_medecin) {
        this.ref_medecin = ref_medecin;
    }

    public int getRef_patient() {
        return ref_patient;
    }

    public void setRef_patient(int ref_patient) {
        this.ref_patient = ref_patient;
    }

    public int getRef_secretaire() {
        return ref_secretaire;
    }

    public void setRef_secretaire(int ref_secretaire) {
        this.ref_secretaire = ref_secretaire;
    }
}
