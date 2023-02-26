package com.hspjava.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Table {
    private int id;

    private final String tableName;

    private ArrayList<Column> columns;

    protected Table() {
        this.tableName = this.getClass().getSimpleName();
        this.setColumns();
    }

    protected Table(String tableName) {
        this.tableName = tableName;
        this.setColumns();
    }

    protected Table(int id) {
        this.id = id;
        this.tableName = this.getClass().getSimpleName();
        this.setColumns();
    }

    protected Table(String tableName, int id) {
        this.tableName = tableName;
        this.id = id;
        this.setColumns();
    }

    public int getId() {
        return id;
    }

    public List<Column> getColumns() {
        return columns;
    }

    private void setColumns() {
        ArrayList<Column> columnArrayList = new ArrayList<>();
        try (PreparedStatement req = Database.getInstance().getCnx().prepareStatement("SHOW COLUMNS FROM " + this.tableName + ";")) {
            ResultSet res = req.executeQuery();
            while (res.next()) {
                columnArrayList.add(new Column(
                        res.getString("Field"),
                        res.getString("Type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.columns = columnArrayList;
    }
}
