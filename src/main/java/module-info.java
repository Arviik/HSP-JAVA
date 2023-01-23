module com.example.hspjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.engine;

    requires mysql;
    requires io.github.palexdev;

    opens com.example.hspjava to javafx.fxml;
    exports com.example.hspjava;
}