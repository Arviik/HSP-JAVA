package com.hspjava;

import com.google.common.base.CaseFormat;
import com.hspjava.services.config.Config;
import com.hspjava.controller.ConnexionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class HspApp extends Application {
    private static Stage stage;

    @Override
    public void start(Stage firstStage) {
        setStage(firstStage);
        stage.setTitle("HSP App");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/icon.png"))));
        changeScene("connexion", new ConnexionController());
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(String fxmlFile, Object controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(HspApp.class.getResource("/view/" + fxmlFile + ".fxml"));
        try {
            fxmlLoader.setController(controller);
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeSceneByUserType(String connectedUserType) {
        FXMLLoader fxmlLoader = new FXMLLoader(HspApp.class.getResource("/view/" + snakeCaseToPascalCase(connectedUserType) + ".fxml"));
        try {
            fxmlLoader.setController(getController(connectedUserType));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object getController(String connectedUserType) {
        for (String userType : Config.getUserTypes()) {
            if (Objects.equals(userType, connectedUserType)) {
                try {
                    return Class.forName("com.hspjava.controller." + snakeCaseToPascalCase(userType) + "Controller").getConstructor().newInstance();
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                         InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static String snakeCaseToPascalCase(String snakeCase) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, snakeCase);
    }

    public static Stage getStage() {
        return stage;
    }

    private static void setStage(Stage stage) {
        HspApp.stage = stage;
    }
}
