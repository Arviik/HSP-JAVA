package com.hspjava.database.repository;

import com.hspjava.database.Database;
import com.hspjava.modele.*;
import com.hspjava.modele.user.Utilisateur;

import java.lang.reflect.Constructor;
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
                switch (table.getColumns().get(i).type()) {
                    case "int" -> req.setInt(i, (Integer) method.invoke(table));
                    case "varchar(255)", "text", "datetime" -> req.setString(i, (String) method.invoke(table));
                    case "tinyint(1)" -> req.setBoolean(i, (Boolean) method.invoke(table));
                    default -> throw new RuntimeException();
                }
            }
            req.executeUpdate();
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public List<? extends Table> getAll(Table table) {
//        try (PreparedStatement req = Database.getInstance().getCnx().prepareStatement("SELECT * FROM " + table.getClass())) {
//            ResultSet res = req.executeQuery();
//            Class<? extends Table> tableClass = table.getClass();
//            Constructor<? extends Table> tableConstructor = tableClass.getConstructor();
//            ArrayList<? extends Table> tables = new ArrayList<>();
//            while (res.next()) {
//                tables.add(tableConstructor.newInstance(res.getMetaData().getColumnCount()));
//            }
//            return tables;
//            //res.getMetaData().getColumnCount()
//        } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException |
//                 IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return Collections.emptyList();
//    }

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
}
