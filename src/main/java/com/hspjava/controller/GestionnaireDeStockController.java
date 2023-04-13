package com.hspjava.controller;

import com.hspjava.database.Database;
import com.hspjava.database.repository.Repository;
import com.hspjava.modele.*;
import com.hspjava.modele.user.ConnectedUser;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.ResourceBundle;

public class GestionnaireDeStockController implements Initializable {
    private final Repository repo = new Repository();

    @FXML
    private MFXComboBox<Demande> demandeField;

    @FXML
    private MFXTableView<Demande> demandeTable;

    @FXML
    private MFXTextField descriptionfield;

    @FXML
    private MFXComboBox<Fournisseur> fournisseurField;

    @FXML
    private MFXTableView<Fournisseur> fournisseurTable;

    @FXML
    private MFXTextField libelleField;

    @FXML
    private MFXTextField nomFournisseurField;

    @FXML
    private MFXComboBox<String> numDangerositeField;

    @FXML
    private MFXTextField prixField;

    @FXML
    private MFXComboBox<Produit> produitField;

    @FXML
    private MFXTableView<Produit> produitTable;

    @FXML
    private MFXTextField stockfield;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupDemandeTable();
        setupFournisseurField();
        setupProduitField();
        setupFournisseurTable();
        setupProduitTable();
        setupDemandeField();
        setupNumDangerositeField();
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

    public void setupFournisseurTable(){
        MFXTableColumn<Fournisseur> nomColumn = new MFXTableColumn<>("Nom", true, Comparator.comparing(Fournisseur::getNom));

        nomColumn.setRowCellFactory(fournisseur -> new MFXTableRowCell<>(Fournisseur::getNom));

        fournisseurTable.getTableColumns().add(nomColumn);

        fournisseurTable.getFilters().add(new StringFilter<>("Nom", Fournisseur::getNom));
    }

    public void setupProduitTable(){
        MFXTableColumn<Produit> libelleColumn = new MFXTableColumn<>("Libelle", true, Comparator.comparing(Produit::getLibelle));
        MFXTableColumn<Produit> descriptionColumn = new MFXTableColumn<>("Description", true, Comparator.comparing(Produit::getDescription));
        MFXTableColumn<Produit> nivDangerositeColumn = new MFXTableColumn<>("Dangérosité", true, Comparator.comparing(Produit::getNiv_dangerosite));
        MFXTableColumn<Produit> stockColumn = new MFXTableColumn<>("Stock", true, Comparator.comparing(Produit::getStock));
        MFXTableColumn<Produit> gestionnaireColumn = new MFXTableColumn<>("Gestionnaire", true, Comparator.comparing(Produit::getRef_gestionnaire_stock));

        libelleColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getLibelle));
        descriptionColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getDescription));
        nivDangerositeColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getNiv_dangerosite));
        stockColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getStock));
        gestionnaireColumn.setRowCellFactory(produit -> new MFXTableRowCell<>(Produit::getRef_gestionnaire_stock));

        produitTable.getTableColumns().add(libelleColumn);
        produitTable.getTableColumns().add(descriptionColumn);
        produitTable.getTableColumns().add(nivDangerositeColumn);
        produitTable.getTableColumns().add(stockColumn);
        produitTable.getTableColumns().add(gestionnaireColumn);

        produitTable.getFilters().add(new StringFilter<>("Libelle", Produit::getLibelle));
        produitTable.getFilters().add(new StringFilter<>("Description", Produit::getDescription));
        produitTable.getFilters().add(new IntegerFilter<>("Dangérosité", Produit::getNiv_dangerosite));
        produitTable.getFilters().add(new IntegerFilter<>("Stock", Produit::getStock));
        produitTable.getFilters().add(new IntegerFilter<>("Gestionnaire", Produit::getRef_gestionnaire_stock));

        ArrayList<? super Produit> listProduit = (ArrayList<? super Produit>) repo.getAll(new Produit());
        produitTable.getItems().addAll((Collection<? extends Produit>) listProduit);
    }

    private void setupDemandeField(){
        ArrayList<? super Demande> dt = (ArrayList<? super Demande>) repo.getAll(new Demande());
        demandeField.getItems().addAll((Collection<? extends Demande>) dt);
        demandeField.setConverter(FunctionalStringConverter.to(demande -> (demande == null) ? "" : String.valueOf(demande.getId())));
    }

    private void setupFournisseurField(){
        ArrayList<? super Fournisseur> ft = (ArrayList<? super Fournisseur>) repo.getAll(new Fournisseur());
        fournisseurField.getItems().addAll((Collection<? extends Fournisseur>) ft);
        fournisseurField.setConverter(FunctionalStringConverter.to(fournisseur -> (fournisseur == null) ? "" : String.valueOf(fournisseur.getId())));
    }

    private void setupProduitField(){
        ArrayList<? super Produit> pt = (ArrayList<? super Produit>) repo.getAll(new Produit());
        produitField.getItems().addAll((Collection<? extends Produit>) pt);
        produitField.setConverter(FunctionalStringConverter.to(produit -> (produit == null) ? "" : String.valueOf(produit.getId())));
    }

    private void setupNumDangerositeField() {
        numDangerositeField.getItems().addAll("1", "2", "3", "4", "5");
        numDangerositeField.setConverter(FunctionalStringConverter.to(string -> (string == null) ? "" : string));
    }
    @FXML
    void onSubmitAddFournisseur(ActionEvent event) {
        Fournisseur fournisseur = new Fournisseur(
                nomFournisseurField.getText()
        );
        repo.save(fournisseur);

    }

    @FXML
    void onSubmitDemande(ActionEvent event) throws SQLException {
        PreparedStatement req = Database.getInstance().getCnx().prepareStatement("UPDATE tache SET est_valide = 1 WHERE id_demande = ?"); {
            req.setInt(1, demandeField.getValue().getId());
        }


    }

    @FXML
    void onSubmitFournisseur(ActionEvent event) throws SQLException {
        PreparedStatement req = Database.getInstance().getCnx().prepareStatement("INSERT INTO Propose (prix,ref_fournisseur,ref_produit) VALUES (?,?,?);"); {
            req.setInt(1, Integer.parseInt(prixField.getText()));
            req.setInt(2, fournisseurField.getValue().getId());
            req.setInt(3, produitField.getValue().getId());
            req.executeUpdate();

        }
    }
    @FXML
    void onSubmitProduit(ActionEvent event) {
        Produit produit = new Produit(
                libelleField.getText(),
                descriptionfield.getText(),
                Integer.parseInt(numDangerositeField.getText()),
                Integer.parseInt(stockfield.getText()),
                ConnectedUser.getInstance().getId()
        );
        repo.save(produit);
        if (produit.getId()!=0){
            libelleField.clear();
            descriptionfield.clear();
            numDangerositeField.clear();
            stockfield.clear();
            produitTable.getItems().add(produit);
            produitField.getItems().add(produit);
        }
    }


}
