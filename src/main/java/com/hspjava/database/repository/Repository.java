package com.hspjava.database.repository;

import com.hspjava.database.Column;
import com.hspjava.database.Database;
import com.hspjava.modele.*;
import com.hspjava.modele.user.Utilisateur;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repository {

    public Table save(Table table) {
        String query;
        if (table.getId() == 0) {
            query = this.generateInsertQuery(table);
        } else {
            query = this.generateUpdateQuery(table);
        }
        try (PreparedStatement req = Database.getInstance().getCnx().prepareStatement(query)) {
            if (table.getId() != 0) {
                req.setInt(table.getColumns().size(), table.getId());
            }
            for (int i = 1; i < table.getColumns().size(); i++) {
                Method method = table.getClass().getMethod("get"
                        + table.getColumns().get(i).field().substring(0, 1).toUpperCase()
                        + table.getColumns().get(i).field().substring(1));
                switch (table.getColumns().get(i).type().split("\\(")[0]) {
                    case "int" -> req.setInt(i, (Integer) method.invoke(table));
                    case "varchar", "text", "datetime" -> req.setString(i, (String) method.invoke(table));
                    case "tinyint" -> req.setBoolean(i, (Boolean) method.invoke(table));
                    default -> throw new RuntimeException();
                }
            }
            req.executeUpdate();
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Patient> getAll(Class<? extends Table> patientClass) {
        try {
           List<Column> maListe = (List<Column>) patientClass.getSuperclass().getMethod("demo").invoke(null);
            System.out.printf(maListe.get(0).field());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return Collections.emptyList();
        //return (List<Patient>) this.generateTableList(table, "SELECT * FROM " + table.getTableName());
    }

    public ArrayList<Table> getAll(Table table) {
        return this.generateTableList(table, "SELECT * FROM " + table.getTableName());
    }

    public List<Table> search(Table table, int field, String value) {
        return this.generateTableList(table, "SELECT * FROM " + table.getTableName() + " WHERE " + table.getColumns().get(field).field() + " = " + value);
    }

    /*public List<Dossier> getAll(Dossier table) {
        return (List<Dossier>) this.generateTableList(table, "SELECT * FROM " + table.getTableName());
    }*/

    private String generateInsertQuery(Table table) {
        StringBuilder query = new StringBuilder("INSERT INTO `"
                + table.getClass().getSimpleName()
                + "` (");
        for (int i = 1; i < table.getColumns().size(); i++) {
            query.append("`").append(table.getColumns().get(i).field()).append("`, ");
        }
        query.delete(query.length() - 2, query.length());
        query.append(") VALUES(");
        query.append("?,".repeat(Math.max(0, table.getColumns().size() - 1)));
        query.delete(query.length() - 1, query.length());
        query.append(");");
        return String.valueOf(query);
    }

    private String generateUpdateQuery(Table table) {
        StringBuilder query = new StringBuilder("UPDATE "
                + table.getClass().getSimpleName()
                + " SET");
        for (int i = 1; i < table.getColumns().size(); i++) {
            query.append(" ").append(table.getColumns().get(i).field()).append(" = ?,");
        }
        query.delete(query.length() - 1, query.length());
        query.append(" WHERE ").append(table.getColumns().get(0).field()).append(" = ?;");
        return String.valueOf(query);
    }

    private ArrayList<Table> generateTableList(Table table, String query) {
        ArrayList<Table> tables = new ArrayList<>();
        try (PreparedStatement req = Database.getInstance().getCnx().prepareStatement(query)) {
            ResultSet res = req.executeQuery();
            switch (table.getTableName()) {
                case "Chambre" -> {
                    while (res.next()) {
                        tables.add(new Chambre(
                                res.getInt(table.getColumns().get(0).field()),
                                res.getInt(table.getColumns().get(1).field())
                        ));
                    }
                }
                case "Demande" -> {
                    while (res.next()) {
                        tables.add(new Demande(
                                res.getInt(table.getColumns().get(0).field()),
                                res.getString(table.getColumns().get(1).field()),
                                res.getBoolean(table.getColumns().get(2).field()),
                                res.getInt(table.getColumns().get(3).field()),
                                res.getInt(table.getColumns().get(4).field())
                        ));
                    }
                }
                case "Dossier" -> {
                    while (res.next()) {
                        tables.add(new Dossier(
                                res.getInt(table.getColumns().get(0).field()),
                                res.getDate(table.getColumns().get(1).field()),
                                res.getString(table.getColumns().get(2).field()),
                                res.getInt(table.getColumns().get(3).field()),
                                res.getInt(table.getColumns().get(4).field()),
                                res.getInt(table.getColumns().get(5).field()),
                                res.getInt(table.getColumns().get(6).field())
                        ));
                    }
                }
                case "Fournisseur" -> {
                    while (res.next()) {
                        tables.add(new Fournisseur(
                                res.getInt(table.getColumns().get(0).field()),
                                res.getString(table.getColumns().get(1).field())
                        ));
                    }
                }
                case "Hospitalisation" -> {
                    while (res.next()) {
                        tables.add(new Hospitalisation(
                                res.getInt(table.getColumns().get(0).field()),
                                res.getDate(table.getColumns().get(1).field()),
                                res.getString(table.getColumns().get(2).field()),
                                res.getInt(table.getColumns().get(3).field()),
                                res.getInt(table.getColumns().get(4).field()),
                                res.getInt(table.getColumns().get(5).field())
                        ));
                    }
                }
                case "Patient" -> {
                    while (res.next()) {
                        tables.add(new Patient(
                                res.getInt(table.getColumns().get(0).field()),
                                res.getString(table.getColumns().get(1).field()),
                                res.getString(table.getColumns().get(2).field()),
                                res.getInt(table.getColumns().get(3).field()),
                                res.getString(table.getColumns().get(4).field()),
                                res.getInt(table.getColumns().get(5).field()),
                                res.getString(table.getColumns().get(6).field()),
                                res.getInt(table.getColumns().get(7).field())
                        ));
                    }
                }
                case "Produit" -> {
                    while (res.next()) {
                        tables.add(new Produit(
                                res.getInt(table.getColumns().get(0).field()),
                                res.getString(table.getColumns().get(1).field()),
                                res.getString(table.getColumns().get(2).field()),
                                res.getInt(table.getColumns().get(3).field()),
                                res.getInt(table.getColumns().get(4).field()),
                                res.getInt(table.getColumns().get(5).field())
                        ));
                    }
                }
                case "Utilisateur" -> {
                    while (res.next()) {
                        tables.add(new Utilisateur(
                                res.getInt(table.getColumns().get(0).field()),
                                res.getString(table.getColumns().get(1).field()),
                                res.getString(table.getColumns().get(2).field()),
                                res.getString(table.getColumns().get(3).field()),
                                res.getString(table.getColumns().get(4).field())
                        ));
                    }
                }
                default -> {
                    return tables;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
        //TODO return tables ou return null ???
    }
}
