package com.hspjava.modele;

import com.hspjava.database.Table;

public class Chambre extends Table {
    private int numero;

    public Chambre(int id, int numero) {
        super(id);
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
