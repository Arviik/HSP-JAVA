package com.hspjava.controller;

import com.hspjava.database.repository.Repository;
import com.hspjava.modele.user.Utilisateur;
import com.hspjava.services.mailer.JMailer;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.mail.MessagingException;
import javafx.fxml.FXML;

import java.security.SecureRandom;

public class MdpOublieController {
    String code;
    Repository repo = new Repository();
    @FXML
    private MFXPasswordField codeField;

    @FXML
    private MFXTextField emailField1;

    @FXML
    void onSubmitcode() {

    }

    @FXML
    void onSubmitmail() throws MessagingException {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        code = sb.toString();
        if (repo.search(new Utilisateur(),3,emailField1.getText()).isEmpty()){

            JMailer.sendmail(emailField1.getText(), code);
        }

    }
}
