package com.hspjava.controller;

import com.hspjava.HspApp;
import com.hspjava.database.repository.UserRepository;
import com.hspjava.modele.user.ConnectedUser;
import com.hspjava.modele.user.Utilisateur;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;

public class ConnexionController {
    @FXML
    private MFXTextField emailField;

    @FXML
    private MFXPasswordField mdpField;

    @FXML
    void onSubmit() throws IOException {
        UserRepository userRepo = new UserRepository();
        Utilisateur user = new Utilisateur(emailField.getText(), mdpField.getText());
        Logger logger = LogManager.getLogger(ConnexionController.class);
        logger.addAppender(new FileAppender(
                new PatternLayout("%d{dd/MM/yyyy HH:mm:ss} [%-5p] - %m%n"),
                "./logs/logs.log"
        ));
        logger.info("Tentative de connexion à l'email :" + user.getEmail());
        if (userRepo.connexion(user)) {
            logger.info("Connexion réussi à l'email : " + ConnectedUser.getInstance().getEmail() +
                    " userType->" + ConnectedUser.getInstance().getUserType()
            );
            HspApp.changeSceneByUserType(ConnectedUser.getInstance().getUserType());
        }
    }

    @FXML
    void onForgotPasswordClicked() {
        HspApp.changeScene("mdpOublie", new MdpOublieController());
    }
}
