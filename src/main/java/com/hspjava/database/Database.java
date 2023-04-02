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
            cnx = DriverManager.getConnection(Config.get("url", String.class), Config.get("username", String.class), Config.get("password", String.class));
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
