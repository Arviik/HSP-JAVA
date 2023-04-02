package com.hspjava.services.config;

import com.github.vincentrussell.ini.Ini;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Config {
    private static Ini ini;

    private Config() {
    }

    public static <T> T get(String key, Class<T> type) {
        return Objects.requireNonNull(getIni()).getValue("Config", key, type);
    }

    public static List<String> getUserTypes() {
        List<String> userTypes = new ArrayList<>();
        Map<String, Object> userTypesMap = Objects.requireNonNull(getIni()).getSection("UserTypes");
        for (Object userType : userTypesMap.values()) {
            userTypes.add((String) userType);
        }
        return userTypes;
    }

    private static Ini getIni() {
        try {
            if (Config.ini == null) {
                String path = "src/main/java/com/hspjava/services/config/dev.ini";
                if (!new File(path).isFile()) {
                    path = "src/main/java/com/hspjava/services/config/prod.ini";
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
