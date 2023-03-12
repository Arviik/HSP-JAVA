package com.hspjava.controller;


import com.hspjava.database.repository.Repository;
import com.hspjava.modele.Concerne;
import com.hspjava.modele.Demande;
import com.hspjava.modele.Hospitalisation;
import com.hspjava.modele.user.ConnectedUser;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MedecinController {
    private final Repository repo = new Repository();

    @FXML
    private MFXComboBox<?> chambreField;

    @FXML
    private MFXDatePicker dateField;

    @FXML
    private MFXTableView<?> demandeTable;

    @FXML
    private MFXTextField descriptionMaladieField;

    @FXML
    private MFXComboBox<?> dossierField;

    @FXML
    private MFXTableView<?> dossierTable;

    @FXML
    private MFXTableView<?> hospitalisationTable;

    @FXML
    private MFXComboBox<?> produitField;

    @FXML
    private MFXTextField quantiteField;

    @FXML
    private MFXTextField raisonField;

    @FXML
    void onSubmitDemande(ActionEvent event) {
        Demande demande = new Demande(
                raisonField.getText(),
                ConnectedUser.getInstance().getId());
        repo.save(demande);
        Concerne concerne = new Concerne(
                Integer.parseInt(quantiteField.getText()),
                demande.getId(),
                Integer.parseInt(produitField.getId())
                );

    }

    @FXML
    void onSubmitHospitalisation(ActionEvent event) throws ParseException {
        Hospitalisation hospitalisation = new Hospitalisation(
                new SimpleDateFormat("yyyy-MM-dd").parse(dateField.getValue().toString()),
                descriptionMaladieField.getText(),
                Integer.parseInt(dossierField.getId()),
                Integer.parseInt(chambreField.getId()));
        repo.save(hospitalisation);

    }

}