package modele;

public class Demande {
    private int id_demande;
    private String raison;
    private int est_valide;
    private int ref_gestionnaire_de_stock;
    private int ref_medecin;

    public int getId_demande() {
        return id_demande;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public int getEst_valide() {
        return est_valide;
    }

    public void setEst_valide(int est_valide) {
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
