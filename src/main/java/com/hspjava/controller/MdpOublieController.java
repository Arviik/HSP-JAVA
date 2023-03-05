package com.hspjava.controller;

import com.hspjava.database.repository.Repository;
import com.hspjava.modele.Code;
import com.hspjava.modele.Table;
import com.hspjava.modele.user.Utilisateur;
import com.hspjava.services.mailer.JMailer;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.mail.MessagingException;
import javafx.fxml.FXML;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MdpOublieController {
    String codes;
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
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        List<? super Utilisateur> user = repo.search(new Utilisateur(), 3, emailField1.getText());
        codes = sb.toString();

        if (repo.search(new Utilisateur(), 3, emailField1.getText()).isEmpty()){
            System.out.println("vide");
            System.out.println(emailField1.getText());
        }
        else{
            System.out.println(user);
            System.out.println(user.get(0));
            calendar.add(Calendar.DATE, 1);
            Code code= new Code(
                    codes,
                    sdf.format(calendar.getTime()),
                    3);
            repo.save(code);
            JMailer.sendmail(emailField1.getText(), codes);
        }

    }
}
