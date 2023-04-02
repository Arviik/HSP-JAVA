package com.hspjava.controller;


import com.hspjava.database.Database;
import com.hspjava.database.repository.Repository;
import com.hspjava.modele.*;
import com.hspjava.modele.user.ConnectedUser;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.BooleanFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        setupChambreField();
        setupDossierField();
        setupProduitField();
    }

    public void setupDemandeTable(){
        MFXTableColumn<Demande> raisonColumn = new MFXTableColumn<>("Raison", true, Comparator.comparing(Demande::getRaison));
        MFXTableColumn<Demande> estValideColumn = new MFXTableColumn<>("Valide", true, Comparator.comparing(Demande::getEst_valide));

        raisonColumn.setRowCellFactory(demande -> new MFXTableRowCell<>(Demande::getRaison));
        estValideColumn.setRowCellFactory(demande -> new MFXTableRowCell<>(Demande::getEst_valide));

        demandeTable.getTableColumns().add(raisonColumn);
        demandeTable.getTableColumns().add(estValideColumn);

        demandeTable.getFilters().add(new StringFilter<>("Raison", Demande::getRaison));
        demandeTable.getFilters().add(new BooleanFilter<>("Valide", Demande::getEst_valide));

        ArrayList<? super Demande> listDemande = (ArrayList<? super Demande>) repo.getAll(new Demande());
        //ArrayList<? super Patient> st2 = (ArrayList<? super Patient>) repo.search(new Patient(), 6, "test2");
        demandeTable.getItems().addAll((Collection<? extends Demande>) listDemande);

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
        dossierTable.getItems().addAll((Collection<? extends Dossier>) listDossier);
    }

    private void setupChambreField() {
        ArrayList<? super Chambre> st = (ArrayList<? super Chambre>) repo.getAll(new Chambre());
        chambreField.getItems().addAll((Collection<? extends Chambre>) st);
        chambreField.setConverter(FunctionalStringConverter.to(chambre -> (chambre == null) ? "" : String.valueOf(chambre.getNumero())));
    }

    private void setupDossierField() {
        ArrayList<? super Dossier> dt = (ArrayList<? super Dossier>) repo.getAll(new Dossier());
        dossierField.getItems().addAll((Collection<? extends Dossier>) dt);
        dossierField.setConverter(FunctionalStringConverter.to(dossier -> (dossier == null) ? "" : String.valueOf(dossier.getDate_heure())));
    }

    private void setupProduitField() {
        ArrayList<? super Produit> pt = (ArrayList<? super Produit>) repo.getAll(new Produit());
        produitField.getItems().addAll((Collection<? extends Produit>) pt);
        produitField.setConverter(FunctionalStringConverter.to(produit -> (produit == null) ? "" : String.valueOf(produit.getLibelle())));
    }

    @FXML
    void onSubmitDemande() throws SQLException {
        Demande demande = new Demande(
                raisonField.getText(),
                ConnectedUser.getInstance().getId()
        );
        repo.save(demande);

        PreparedStatement req = Database.getInstance().getCnx().prepareStatement("INSERT INTO Concerne (quantite,ref_demande,ref_produit) VALUES (?,?,?);"); {
            req.setInt(1, Integer.parseInt(quantiteField.getText()));
            req.setInt(2, demande.getId());
            req.setInt(3, produitField.getValue().getId());
            req.executeUpdate();
        }
    }

    @FXML
    void onSubmitHospitalisation() throws ParseException {
        Hospitalisation hospitalisation = new Hospitalisation(
                new SimpleDateFormat("yyyy-MM-dd").parse(dateField.getValue().toString()),
                descriptionMaladieField.getText(),
                dossierField.getValue().getId(),
                chambreField.getValue().getId());
        repo.save(hospitalisation);

    }

}