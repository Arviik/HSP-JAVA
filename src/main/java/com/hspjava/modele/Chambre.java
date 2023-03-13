package com.hspjava.modele;

public class Chambre extends Table {
    private int numero;

    public Chambre(){

    }
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
