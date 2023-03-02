package com.hspjava.modele;

import java.util.Date;

public class Code {
    private String code_text;

    private Date date_peremption;

    private int ref_utilisateur;

    public Code(String code_text, Date date_peremption, int ref_utilisateur){
        this.code_text=code_text;
        this.date_peremption=date_peremption;
        this.ref_utilisateur=ref_utilisateur;
    }
}
