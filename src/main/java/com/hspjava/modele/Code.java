package com.hspjava.modele;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Code extends Table{
    private String code_text;

    private String date_peremption;

    private int ref_utilisateur;

    public Code(String code_text, String date_peremption, int ref_utilisateur){
        this.code_text=code_text;
        this.date_peremption=date_peremption;
        this.ref_utilisateur=ref_utilisateur;
    }




    public String getCode_text() {
        return code_text;
    }

    public void setCode_text(String code_text) {
        this.code_text = code_text;
    }

    public String getDate_peremption() {
        return date_peremption;
    }

    public void setDate_peremption(String date_peremption) {
        this.date_peremption = date_peremption;
    }

    public int getRef_utilisateur() {
        return ref_utilisateur;
    }

    public void setRef_utilisateur(int ref_utilisateur) {
        this.ref_utilisateur = ref_utilisateur;
    }
}
