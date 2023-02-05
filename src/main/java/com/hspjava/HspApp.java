package com.hspjava;

import com.hspjava.controller.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class HspApp extends Application {
    private static Stage stage;

    @Override
    public void start(Stage firstStage) {
        setStage(firstStage);
        stage.setTitle("HSP App");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/icon.png"))));
        changeScene("hello-view", new HelloController());
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

    public static Stage getStage() {
        return stage;
    }

    private static void setStage(Stage stage) {
        HspApp.stage = stage;
    }
}