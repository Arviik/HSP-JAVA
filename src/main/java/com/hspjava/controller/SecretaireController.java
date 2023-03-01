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
import java.util.Comparator;
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
    private MFXTableView<?> dossierTable;

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
    private MFXTableView<Patient> patientTable;

    @FXML
    private MFXTextField prenomField;

    @FXML
    private MFXTextField telephoneField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MFXTableColumn<Patient> nomColumn = new MFXTableColumn<>("Nom", true, Comparator.comparing(Patient::getNom));
        MFXTableColumn<Patient> prenomColumn = new MFXTableColumn<>("Prénom", true, Comparator.comparing(Patient::getPrenom));
        MFXTableColumn<Patient> numSecuriteSocialColumn = new MFXTableColumn<>("Numéro de sécurité sociale", true, Comparator.comparing(Patient::getNum_securite_sociale));
        MFXTableColumn<Patient> emailColumn = new MFXTableColumn<>("Email", true, Comparator.comparing(Patient::getEmail));
        MFXTableColumn<Patient> telphoneColumn = new MFXTableColumn<>("Téléphone", true, Comparator.comparing(Patient::getTelephone));
        MFXTableColumn<Patient> adresseColumn = new MFXTableColumn<>("Adresse", true, Comparator.comparing(Patient::getAdresse));

        nomColumn.setRowCellFactory(patient -> new MFXTableRowCell<>(Patient::getNom));
        prenomColumn.setRowCellFactory(patient -> new MFXTableRowCell<>(Patient::getPrenom));
        numSecuriteSocialColumn.setRowCellFactory(patient -> new MFXTableRowCell<>(Patient::getNum_securite_sociale));
        emailColumn.setRowCellFactory(patient -> new MFXTableRowCell<>(Patient::getEmail));
        telphoneColumn.setRowCellFactory(patient -> new MFXTableRowCell<>(Patient::getTelephone));
        adresseColumn.setRowCellFactory(patient -> new MFXTableRowCell<>(Patient::getAdresse));

        patientTable.getTableColumns().add(nomColumn);
        patientTable.getTableColumns().add(prenomColumn);
        patientTable.getTableColumns().add(numSecuriteSocialColumn);
        patientTable.getTableColumns().add(emailColumn);
        patientTable.getTableColumns().add(telphoneColumn);
        patientTable.getTableColumns().add(adresseColumn);

        patientTable.getFilters().add(new StringFilter<>("Nom", Patient::getNom));
        patientTable.getFilters().add(new StringFilter<>("Prénom", Patient::getPrenom));
        patientTable.getFilters().add(new IntegerFilter<>("Numéro de sécurité sociale", Patient::getNum_securite_sociale));
        patientTable.getFilters().add(new StringFilter<>("Email", Patient::getEmail));
        patientTable.getFilters().add(new IntegerFilter<>("Téléphone", Patient::getTelephone));
        patientTable.getFilters().add(new StringFilter<>("Adresse", Patient::getAdresse));

        //patientTable.getItems().add((Patient) repo.getAll(new Patient()));
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

    @FXML
    void onSubmitDossier() {
//        Dossier dossier = new Dossier(
//                dateField.getCurrentDate() + heureField.getText(),
//                descriptionSymptomeField.getText(),
//                numGraviteField.getText(),
//        );
    }
}
