package com.hspjava.controller;

import com.hspjava.config.Config;
import com.hspjava.database.repository.UserRepository;
import com.hspjava.modele.user.Utilisateur;
import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private final UserRepository userRepo = new UserRepository();
    @FXML
    private MFXTextField emailField;

    @FXML
    private MFXPasswordField mdpField;

    @FXML
    private MFXTextField nomField;

    @FXML
    private MFXTextField prenomField;

    @FXML
    private MFXTableView<Utilisateur> userTable;

    @FXML
    private MFXComboBox<String> userTypeField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MFXTableColumn<Utilisateur> nomColumn = new MFXTableColumn<>("Nom", true, Comparator.comparing(Utilisateur::getNom));
        MFXTableColumn<Utilisateur> prenomColumn = new MFXTableColumn<>("Prénom", true, Comparator.comparing(Utilisateur::getPrenom));
        MFXTableColumn<Utilisateur> emailColumn = new MFXTableColumn<>("Email", true, Comparator.comparing(Utilisateur::getEmail));
        MFXTableColumn<Utilisateur> typeColumn = new MFXTableColumn<>("Type", true, Comparator.comparing(Utilisateur::getUserType));

        nomColumn.setRowCellFactory(user -> new MFXTableRowCell<>(Utilisateur::getNom));
        prenomColumn.setRowCellFactory(user -> new MFXTableRowCell<>(Utilisateur::getPrenom));
        emailColumn.setRowCellFactory(user -> new MFXTableRowCell<>(Utilisateur::getEmail));
        typeColumn.setRowCellFactory(user -> new MFXTableRowCell<>(Utilisateur::getUserType));

        userTable.getTableColumns().add(nomColumn);
        userTable.getTableColumns().add(prenomColumn);
        userTable.getTableColumns().add(emailColumn);
        userTable.getTableColumns().add(typeColumn);

        userTable.getFilters().add(new StringFilter<>("Nom", Utilisateur::getNom));
        userTable.getFilters().add(new StringFilter<>("Prénom", Utilisateur::getPrenom));
        userTable.getFilters().add(new StringFilter<>("Email", Utilisateur::getEmail));
        userTable.getFilters().add(new StringFilter<>("Type", Utilisateur::getUserType));

        ArrayList<? super Utilisateur> listUser = (ArrayList<? super Utilisateur>) userRepo.getAll(new Utilisateur());
        userTable.getItems().addAll((Collection<? extends Utilisateur>) listUser);

        for (String userType : Config.getUserTypes()) {
            userTypeField.getItems().add(userType);
        }
        userTypeField.setConverter(FunctionalStringConverter.to(userType -> (userType == null) ? "" : userType));
    }

    @FXML
    void onSubmitUser() {
        Utilisateur user = new Utilisateur(
                nomField.getText(),
                prenomField.getText(),
                emailField.getText(),
                Password.hash(mdpField.getText()).with(BcryptFunction.getInstance(Bcrypt.valueOf(Config.get("bcrypt_version", String.class)), Config.get("bcrypt_cost_factor", Integer.class))).getResult()
        );
        userRepo.save(user);
        user.setUserType(userTypeField.getValue());
        if (user.getId() != 0) {
            nomField.clear();
            prenomField.clear();
            emailField.clear();
            mdpField.clear();
            userTypeField.clear();
            userTable.getItems().add(user);
        }
    }

    @FXML
    void onLogoutButtonClick() {

    }

    @FXML
    void onRowClicked() {

    }
}
