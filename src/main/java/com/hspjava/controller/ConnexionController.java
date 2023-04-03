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

public class ConnexionController {
    @FXML
    private MFXTextField emailField;

    @FXML
    private MFXPasswordField mdpField;

    @FXML
    void onSubmit() {
        UserRepository userRepo = new UserRepository();
        Utilisateur user = new Utilisateur(emailField.getText(), mdpField.getText());
        Logger logger = LogManager.getLogger(ConnexionController.class);
        FileAppender fileAppender = new FileAppender();
        fileAppender.setName("FileAppender");
        fileAppender.setFile("src/main/java/com/hspjava/services/logger/logs.log");
        logger.addAppender(fileAppender);
        logger.info("Tentative de connexion: email->" + user.getEmail());
        if (userRepo.connexion(user)) {
            logger.info("Connexion réussi: email->" + ConnectedUser.getInstance().getEmail() +
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
