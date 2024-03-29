package com.hspjava.controller;

import com.hspjava.database.repository.Repository;
import com.hspjava.modele.Dossier;
import com.hspjava.modele.Patient;
import com.hspjava.modele.user.ConnectedUser;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.ResourceBundle;

import static com.hspjava.HspApp.changeScene;

public class SecretaireController implements Initializable {
    private final Repository repo = new Repository();

    @FXML
    private MFXTextField adresseField;

    @FXML
    private MFXDatePicker dateField;

    @FXML
    private MFXTextField descriptionSymptomeField;

    @FXML
    private MFXTableView<Dossier> dossierTable;

    @FXML
    private MFXTextField emailField;

    @FXML
    private MFXTextField heureField;

    @FXML
    private MFXTextField nomField;

    @FXML
    private MFXComboBox<String> numGraviteField;

    @FXML
    private MFXTextField numSecuriteSocialField;

    @FXML
    private MFXComboBox<Patient> patientField;

    @FXML
    private MFXTableView<Patient> patientTable;

    @FXML
    private MFXTextField prenomField;

    @FXML
    private MFXTextField telephoneField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupDossierTable();
        setupPatientTable();
        setupPatientField();
        setupNumGraviteField();
    }

    private void setupDossierTable() {
        MFXTableColumn<Dossier> dateHeureColumn = new MFXTableColumn<>("Date et heure", true, Comparator.comparing(Dossier::getDate_heure));
        MFXTableColumn<Dossier> descriptionSymptomeColumn = new MFXTableColumn<>("Symptome", true, Comparator.comparing(Dossier::getDescription_symptome));
        MFXTableColumn<Dossier> ordonnanceColumn = new MFXTableColumn<>("Ordonnace", true, Comparator.comparing(Dossier::getOrdonnance));
        MFXTableColumn<Dossier> nivGraviteColumn = new MFXTableColumn<>("Gravité", true, Comparator.comparing(Dossier::getNiv_gravite));
        MFXTableColumn<Dossier> medecinColumn = new MFXTableColumn<>("Medecin", true, Comparator.comparing(Dossier::getNiv_gravite));
        MFXTableColumn<Dossier> patientColumn = new MFXTableColumn<>("Patient", true, Comparator.comparing(Dossier::getRef_patient));
        MFXTableColumn<Dossier> secretaireColumn = new MFXTableColumn<>("Secretaire", true, Comparator.comparing(Dossier::getRef_secretaire));

        dateHeureColumn.setRowCellFactory(dossier -> new MFXTableRowCell<>(Dossier::getDate_heure));
        descriptionSymptomeColumn.setRowCellFactory(dossier -> new MFXTableRowCell<>(Dossier::getDescription_symptome));
        ordonnanceColumn.setRowCellFactory(dossier -> new MFXTableRowCell<>(Dossier::getOrdonnance));
        nivGraviteColumn.setRowCellFactory(dossier -> new MFXTableRowCell<>(Dossier::getNiv_gravite));
        medecinColumn.setRowCellFactory(dossier -> new MFXTableRowCell<>(Dossier::getRef_medecin));
        patientColumn.setRowCellFactory(dossier -> new MFXTableRowCell<>(Dossier::getRef_patient));
        secretaireColumn.setRowCellFactory(dossier -> new MFXTableRowCell<>(Dossier::getRef_secretaire));

        dossierTable.getTableColumns().add(dateHeureColumn);
        dossierTable.getTableColumns().add(descriptionSymptomeColumn);
        dossierTable.getTableColumns().add(ordonnanceColumn);
        dossierTable.getTableColumns().add(nivGraviteColumn);
        dossierTable.getTableColumns().add(medecinColumn);
        dossierTable.getTableColumns().add(patientColumn);
        dossierTable.getTableColumns().add(secretaireColumn);

        dossierTable.getFilters().add(new StringFilter<>("Date et heure", Dossier::getDate_heure));
        dossierTable.getFilters().add(new StringFilter<>("Symptome", Dossier::getDescription_symptome));
        dossierTable.getFilters().add(new StringFilter<>("Ordonnace", Dossier::getOrdonnance));
        dossierTable.getFilters().add(new IntegerFilter<>("Gravité", Dossier::getNiv_gravite));
        dossierTable.getFilters().add(new IntegerFilter<>("Medecin", Dossier::getNiv_gravite));
        dossierTable.getFilters().add(new IntegerFilter<>("Patient", Dossier::getRef_patient));
        dossierTable.getFilters().add(new IntegerFilter<>("Secretaire", Dossier::getRef_secretaire));

        ArrayList<? super Dossier> listDossier = (ArrayList<? super Dossier>) repo.getAll(new Dossier());
        dossierTable.getItems().addAll((Collection<? extends Dossier>) listDossier);
    }

    private void setupPatientTable() {
        MFXTableColumn<Patient> nomColumn = new MFXTableColumn<>("Nom", true, Comparator.comparing(Patient::getNom));
        MFXTableColumn<Patient> prenomColumn = new MFXTableColumn<>("Prénom", true, Comparator.comparing(Patient::getPrenom));
        MFXTableColumn<Patient> numSecuriteSocialColumn = new MFXTableColumn<>("Numéro de sécurité sociale", true, Comparator.comparing(Patient::getNum_securite_sociale));
        MFXTableColumn<Patient> emailColumn = new MFXTableColumn<>("Email", true, Comparator.comparing(Patient::getEmail));
        MFXTableColumn<Patient> telephoneColumn = new MFXTableColumn<>("Téléphone", true, Comparator.comparing(Patient::getTelephone));
        MFXTableColumn<Patient> adresseColumn = new MFXTableColumn<>("Adresse", true, Comparator.comparing(Patient::getAdresse));

        nomColumn.setRowCellFactory(patient -> new MFXTableRowCell<>(Patient::getNom));
        prenomColumn.setRowCellFactory(patient -> new MFXTableRowCell<>(Patient::getPrenom));
        numSecuriteSocialColumn.setRowCellFactory(patient -> new MFXTableRowCell<>(Patient::getNum_securite_sociale));
        emailColumn.setRowCellFactory(patient -> new MFXTableRowCell<>(Patient::getEmail));
        telephoneColumn.setRowCellFactory(patient -> new MFXTableRowCell<>(Patient::getTelephone));
        adresseColumn.setRowCellFactory(patient -> new MFXTableRowCell<>(Patient::getAdresse));

        patientTable.getTableColumns().add(nomColumn);
        patientTable.getTableColumns().add(prenomColumn);
        patientTable.getTableColumns().add(numSecuriteSocialColumn);
        patientTable.getTableColumns().add(emailColumn);
        patientTable.getTableColumns().add(telephoneColumn);
        patientTable.getTableColumns().add(adresseColumn);

        patientTable.getFilters().add(new StringFilter<>("Nom", Patient::getNom));
        patientTable.getFilters().add(new StringFilter<>("Prénom", Patient::getPrenom));
        patientTable.getFilters().add(new IntegerFilter<>("Numéro de sécurité sociale", Patient::getNum_securite_sociale));
        patientTable.getFilters().add(new StringFilter<>("Email", Patient::getEmail));
        patientTable.getFilters().add(new IntegerFilter<>("Téléphone", Patient::getTelephone));
        patientTable.getFilters().add(new StringFilter<>("Adresse", Patient::getAdresse));

        ArrayList<? super Patient> listPatient = (ArrayList<? super Patient>) repo.getAll(new Patient());
        //ArrayList<? super Patient> st2 = (ArrayList<? super Patient>) repo.search(new Patient(), 6, "test2");
        patientTable.getItems().addAll((Collection<? extends Patient>) listPatient);
    }

    private void setupPatientField() {
        ArrayList<? super Patient> st = (ArrayList<? super Patient>) repo.getAll(new Patient());
        patientField.getItems().addAll((Collection<? extends Patient>) st);
        patientField.setConverter(FunctionalStringConverter.to(patient -> (patient == null) ? "" : patient.getNom() + " " + patient.getPrenom()));
    }

    private void setupNumGraviteField() {
        numGraviteField.getItems().addAll("1", "2", "3", "4", "5");
        numGraviteField.setConverter(FunctionalStringConverter.to(string -> (string == null) ? "" : string));
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
        if (patient.getId() != 0) {
            nomField.clear();
            prenomField.clear();
            numSecuriteSocialField.clear();
            emailField.clear();
            telephoneField.clear();
            adresseField.clear();
            patientTable.getItems().add(patient);
            patientField.getItems().add(patient);
        }
    }

    @FXML
    void onSubmitDossier() throws ParseException {
        //TODO initialiser Date avec la valeur de epoch
        Dossier dossier = new Dossier(
                new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateField.getValue().toString() + " " + heureField.getText()),
                descriptionSymptomeField.getText(),
                "",
                Integer.parseInt(numGraviteField.getText()),
                0,
                patientField.getValue().getId(),
                ConnectedUser.getInstance().getId()
        );
        repo.save(dossier);
        if (dossier.getId() != 0) {
            dateField.clear();
            heureField.clear();
            descriptionSymptomeField.clear();
            numGraviteField.clear();
            patientField.clear();
            dossierTable.getItems().add(dossier);
        }
    }

    @FXML
    void onRowClicked(MouseEvent event) {

    }

    @FXML
    void onLogoutButtonClick() {
        changeScene("connexion", new ConnexionController());
    }

}