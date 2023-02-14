package com.hspjava.modele.user;

public class ConnectedUser extends Utilisateur {
    private static volatile ConnectedUser instance;

    private ConnectedUser() {
        super("Utilisateur");
    }

    private ConnectedUser(int id, String nom, String prenom, String email, String mot_de_passe) {
        super("Utilisateur", id, nom, prenom, email, mot_de_passe);
    }

    public static ConnectedUser getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (ConnectedUser.class) {
            if (instance == null) {
                instance = new ConnectedUser();
            }
            return instance;
        }
    }

    public static ConnectedUser getInstance(int id, String nom, String prenom, String email, String mot_de_passe) {
        if (instance != null) {
            return instance;
        }
        synchronized (ConnectedUser.class) {
            if (instance == null) {
                instance = new ConnectedUser(id, nom, prenom, email, mot_de_passe);
            }
            return instance;
        }
    }
}
