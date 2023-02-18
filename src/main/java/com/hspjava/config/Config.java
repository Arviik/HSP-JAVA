package com.hspjava.config;

import com.github.vincentrussell.ini.Ini;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class Config {
    private static Ini ini;

    private Config() {
    }

    public static String get(String name) {
        return Objects.requireNonNull(getIni()).getValue("Config", name, String.class);
    }

    private static Ini getIni() {
        try {
            if (Config.ini == null) {
                String path = "src/main/java/com/hspjava/config/dev.ini";
                if (!new File(path).isFile()) {
                    path = "src/main/java/com/hspjava/config/prod.ini";
                }
                ini = new Ini();
                ini.load(new FileInputStream(path));
            }
            return ini;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
