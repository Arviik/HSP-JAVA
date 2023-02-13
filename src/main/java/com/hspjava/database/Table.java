package com.hspjava.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Table {
    private int id;

    private ArrayList<Column> columns;

    protected Table(int id) {
        this.id = id;
        this.setColumns();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Column> getColumns() {
        return columns;
    }

    private void setColumns() {
        ArrayList<Column> columnArrayList = new ArrayList<>();
        try {
            PreparedStatement req = Database.getInstance().getCnx().prepareStatement("SHOW COLUMNS FROM " + this.getClass().getSimpleName() + ";");
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
