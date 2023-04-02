package com.hspjava.controller;

import com.hspjava.HspApp;
import com.hspjava.database.repository.UserRepository;
import com.hspjava.modele.user.ConnectedUser;
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
            if (ConnectedUser.getInstance().getUserType().equals("gestionnaire_de_stock")){
                HspApp.changeScene("gestionnaireDeStock", new GestionnaireDeStockController());
            }
            else{
                HspApp.changeSceneByUserType(ConnectedUser.getInstance().getUserType());
            }
        }
    }

    @FXML
    void onForgotPasswordClicked() {
        HspApp.changeScene("mdpOublie", new MdpOublieController());
    }
}
