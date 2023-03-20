package com.hspjava.database.repository;

import com.hspjava.config.Config;
import com.hspjava.database.Database;
import com.hspjava.modele.user.ConnectedUser;
import com.hspjava.modele.user.Utilisateur;
import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends Repository {

    public boolean connexion(Utilisateur user) {
        try (PreparedStatement req = Database.getInstance().getCnx().prepareStatement("SELECT * FROM utilisateur WHERE email = ?")) {
            req.setString(1, user.getEmail());
            ResultSet res = req.executeQuery();
            if (res.next() && Password.check(user.getMot_de_passe(), res.getString(user.getColumns().get(4).field())).with(BcryptFunction.getInstance(Bcrypt.valueOf(Config.get("bcrypt_version")), Integer.parseInt(Config.get("bcrypt_cost_factor"))))) {
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
