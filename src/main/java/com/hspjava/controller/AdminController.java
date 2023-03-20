package com.hspjava.controller;

import com.hspjava.database.repository.Repository;
import com.hspjava.modele.user.Utilisateur;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;

public class AdminController {
    private final Repository repo = new Repository();
    @FXML
    private MFXTextField emailField;

    @FXML
    private MFXPasswordField mdpField;

    @FXML
    private MFXTextField nomField;

    @FXML
    private MFXTextField prenomField;

    @FXML
    private MFXTableView<?> userTable;

    @FXML
    void onSubmitPatient() {
        Utilisateur user = new Utilisateur(
                nomField.getText(),
                prenomField.getText(),
                emailField.getText(),
                mdpField.getText()
        );
        repo.save(user);
        if (user.getId() != 0) {
            nomField.clear();
            prenomField.clear();
            emailField.clear();
            mdpField.clear();
        }
    }

    @FXML
    void onLogoutButtonClick() {

    }

    @FXML
    void onRowClicked() {

    }
}
