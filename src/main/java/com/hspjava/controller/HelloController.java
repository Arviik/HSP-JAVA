package com.hspjava.controller;

import com.hspjava.database.repository.Repository;
import com.hspjava.modele.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        Repository repo = new Repository();
        Patient table = new Patient(1,"test patient n2","test patient p2",946482," test patient mail2",6856202,"test patient rue de je sais pas2",1);

        repo.save(table);
    }
}