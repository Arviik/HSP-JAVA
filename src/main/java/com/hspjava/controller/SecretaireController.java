package com.hspjava.controller;

import com.hspjava.database.repository.Repository;
import com.hspjava.modele.Patient;
import com.hspjava.modele.user.ConnectedUser;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SecretaireController implements Initializable {
    private final Repository repo = new Repository();

    @FXML
    private MFXTextField adresseField;

    @FXML
    private MFXDatePicker dateField;

    @FXML
    private MFXTextField descriptionSymptomeField;

    @FXML
    private MFXTextField emailField;

    @FXML
    private MFXTextField heureField;

    @FXML
    private MFXTextField nomField;

    @FXML
    private MFXComboBox<?> numGraviteField;

    @FXML
    private MFXTextField numSecuriteSocialField;

    @FXML
    private MFXComboBox<?> patientField;

    @FXML
    private MFXTextField prenomField;

    @FXML
    private MFXTextField telephoneField;

    @FXML
    void onSubmitDossier() {

    }

    @FXML
    void onSubmitPatient() {
        Patient patient = new Patient(
                nomField.getText(),
                prenomField.getText(),
                Integer.parseInt(numSecuriteSocialField.getText()),
                emailField.getText(),
                Integer.parseInt(telephoneField.getText()),
                adresseField.getText(),
                ConnectedUser.getInstance().getId()
        );
        repo.save(patient);
    }

    }

}
