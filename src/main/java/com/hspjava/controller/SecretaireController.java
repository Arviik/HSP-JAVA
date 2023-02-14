package com.hspjava.controller;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SecretaireController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

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

    }

}
