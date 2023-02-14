package com.hspjava.database.repository;

import com.hspjava.database.Database;
import com.hspjava.modele.user.ConnectedUser;
import com.hspjava.modele.user.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends Repository {

    public boolean connexion(Utilisateur user) {
        try {
            PreparedStatement req = Database.getInstance().getCnx().prepareStatement("SELECT  * FROM utilisateur WHERE email = ? AND mot_de_passe = ?");
            req.setString(1, user.getEmail());
            req.setString(2, user.getMot_de_passe());
            ResultSet res = req.executeQuery();
            if (res.next()) {
                ConnectedUser.getInstance(
                        res.getInt(user.getColumns().get(0).field()),
                        res.getString(user.getColumns().get(1).field()),
                        res.getString(user.getColumns().get(2).field()),
                        res.getString(user.getColumns().get(3).field()),
                        res.getString(user.getColumns().get(4).field())
                );
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
