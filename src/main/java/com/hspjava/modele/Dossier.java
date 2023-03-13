package com.hspjava.modele;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dossier extends Table {
    private Date date_heure;
    private String description_symptome;
    private String ordonnance;
    private int niv_gravite;
    private int ref_medecin;
    private int ref_patient;
    private int ref_secretaire;

    public Dossier(){}
    public Dossier(Date date_heure, String description_symptome, String ordonnance, int niv_gravite, int ref_medecin, int ref_patient, int ref_secretaire) {
        this.date_heure = date_heure;
        this.description_symptome = description_symptome;
        this.ordonnance = ordonnance;
        this.niv_gravite = niv_gravite;
        this.ref_medecin = ref_medecin;
        this.ref_patient = ref_patient;
        this.ref_secretaire = ref_secretaire;
    }

    public Dossier(int id, Date date_heure, String description_symptome, String ordonnance, int niv_gravite, int ref_medecin, int ref_patient, int ref_secretaire) {
        super(id);
        this.date_heure = date_heure;
        this.description_symptome = description_symptome;
        this.ordonnance = ordonnance;
        this.niv_gravite = niv_gravite;
        this.ref_medecin = ref_medecin;
        this.ref_patient = ref_patient;
        this.ref_secretaire = ref_secretaire;
    }

    public String getDate_heure() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date_heure);
    }

    public void setDate_heure(Date date_heure) {
        this.date_heure = date_heure;
    }

    public String getDescription_symptome() {
        return description_symptome;
    }

    public void setDescription_symptome(String description_symptome) {
        this.description_symptome = description_symptome;
    }

    public String getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(String ordonnance) {
        this.ordonnance = ordonnance;
    }

    public int getNiv_gravite() {
        return niv_gravite;
    }

    public void setNiv_gravite(int niv_gravite) {
        this.niv_gravite = niv_gravite;
    }

    public Object getRef_medecin() {
        if (ref_medecin == 0) {
            return null;
        }
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
