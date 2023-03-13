package com.hspjava.controller;


import com.hspjava.database.repository.Repository;
import com.hspjava.modele.*;
import com.hspjava.modele.user.ConnectedUser;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MedecinController implements Initializable {
    private final Repository repo = new Repository();

    @FXML
    private MFXComboBox<Chambre> chambreField;

    @FXML
    private MFXDatePicker dateField;

    @FXML
    private MFXTableView<Demande> demandeTable;

    @FXML
    private MFXTextField descriptionMaladieField;

    @FXML
    private MFXComboBox<Dossier> dossierField;

    @FXML
    private MFXTableView<Dossier> dossierTable;

    @FXML
    private MFXTableView<Hospitalisation> hospitalisationTable;

    @FXML
    private MFXComboBox<Produit> produitField;

    @FXML
    private MFXTextField quantiteField;

    @FXML
    private MFXTextField raisonField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupDossierTable();
    }

    public void setupDossierTable(){
        MFXTableColumn<Dossier> dateHeureColumn = new MFXTableColumn<>("Date", true, Comparator.comparing(Dossier::getDate_heure));
        MFXTableColumn<Dossier> descriptionColumn = new MFXTableColumn<>("Description", true, Comparator.comparing(Dossier::getDescription_symptome));
        MFXTableColumn<Dossier> graviteColumn = new MFXTableColumn<>("Niv Gravité", true, Comparator.comparing(Dossier::getNiv_gravite));
        MFXTableColumn<Dossier> patientColumn = new MFXTableColumn<>("Patient", true, Comparator.comparing(Dossier::getRef_patient));

        dateHeureColumn.setRowCellFactory(dossier -> new MFXTableRowCell<>(Dossier::getDate_heure));
        descriptionColumn.setRowCellFactory(dossier -> new MFXTableRowCell<>(Dossier::getDescription_symptome));
        graviteColumn.setRowCellFactory(dossier -> new MFXTableRowCell<>(Dossier::getNiv_gravite));
        patientColumn.setRowCellFactory(dossier -> new MFXTableRowCell<>(Dossier::getRef_patient));

        dossierTable.getTableColumns().add(dateHeureColumn);
        dossierTable.getTableColumns().add(descriptionColumn);
        dossierTable.getTableColumns().add(graviteColumn);
        dossierTable.getTableColumns().add(patientColumn);

        dossierTable.getFilters().add(new StringFilter<>("Date", Dossier::getDate_heure));
        dossierTable.getFilters().add(new StringFilter<>("Description", Dossier::getDescription_symptome));
        dossierTable.getFilters().add(new IntegerFilter<>("Niv Gravité", Dossier::getNiv_gravite));
        dossierTable.getFilters().add(new IntegerFilter<>("Patient", Dossier::getRef_patient));

        ArrayList<? super Dossier> listDossier = (ArrayList<? super Dossier>) repo.getAll(new Dossier());

    }

    private void setupChambreField() {
        ArrayList<? super Chambre> st = (ArrayList<? super Chambre>) repo.getAll(new Chambre());
        chambreField.getItems().addAll((Collection<? extends Chambre>) st);
        chambreField.setConverter(FunctionalStringConverter.to(chambre -> (chambre == null) ? "" : String.valueOf(chambre.getNumero())));
    }

    private void setupDossierField() {
        ArrayList<? super Dossier> dt = (ArrayList<? super Dossier>) repo.getAll(new Dossier());
        dossierField.getItems().addAll((Collection<? extends Dossier>) dt);
        dossierField.setConverter(FunctionalStringConverter.to(dossier -> (dossier == null) ? "" : String.valueOf(chambre.getNumero())));
    }
    @FXML
    void onSubmitDemande(ActionEvent event) {
        Demande demande = new Demande(
                raisonField.getText(),
                ConnectedUser.getInstance().getId());
        repo.save(demande);
        Concerne concerne = new Concerne(
                Integer.parseInt(quantiteField.getText()),
                demande.getId(),
                produitField.getValue().getId()
                );
        repo.save(concerne);

    }

    @FXML
    void onSubmitHospitalisation(ActionEvent event) throws ParseException {
        Hospitalisation hospitalisation = new Hospitalisation(
                new SimpleDateFormat("yyyy-MM-dd").parse(dateField.getValue().toString()),
                descriptionMaladieField.getText(),
                dossierField.getValue().getId(),
                chambreField.getValue().getId());
        repo.save(hospitalisation);

    }

}