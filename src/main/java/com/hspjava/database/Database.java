package com.hspjava.database;

import com.hspjava.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {
    private static Database instance;
    private Connection cnx;

    private Database() {
        try {
            cnx = DriverManager.getConnection(Config.get("url"), Config.get("username"), Config.get("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (Database.class) {
            if (instance == null) {
                instance = new Database();
            }
            return instance;
        }
    }

    public Connection getCnx() {
        return cnx;
    }
}
