package com.hspjava.database;

import com.hspjava.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {
    private static volatile Database instance;
    private Connection cnx;

    private Database() {
    }

    public static Database getInstance() {
        Database result = instance;
        if (result != null) {
            return result;
        }
        synchronized (Database.class) {
            if (instance == null) {
                instance = new Database();
            }
            return instance;
        }
    }

    public Connection getCnx() {
        try {
            cnx = DriverManager.getConnection(Config.get("host") + ";" + Config.get("dbname") + ";" + Config.get("charset"), Config.get("username"), Config.get("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnx;
    }
}
