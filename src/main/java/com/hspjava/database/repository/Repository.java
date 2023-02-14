package com.hspjava.database.repository;

import com.hspjava.database.Database;
import com.hspjava.database.Table;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Repository {

    public Table save(Table table) {
        try {
            PreparedStatement req;
            if (table.getId() == 0) {
                req = Database.getInstance().getCnx().prepareStatement(this.generateInsertQuery(table));
            } else {
                req = Database.getInstance().getCnx().prepareStatement(this.generateUpdateQuery(table));
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
