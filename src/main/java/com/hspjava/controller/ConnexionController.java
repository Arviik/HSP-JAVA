package com.hspjava.controller;

import com.hspjava.HspApp;
import com.hspjava.database.repository.UserRepository;
import com.hspjava.modele.user.Utilisateur;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;

public class ConnexionController {
    @FXML
    private MFXTextField emailField;

    @FXML
    private MFXPasswordField mdpField;

    @FXML
    void onSubmit() {
        UserRepository userRepo = new UserRepository();
        Utilisateur user = new Utilisateur(emailField.getText(), mdpField.getText());
        if (userRepo.connexion(user)) {
            System.out.println("Connection réussi.");
            HspApp.changeScene("secretaire", new SecretaireController());
        }
    }
}