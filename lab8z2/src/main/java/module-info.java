module org.example.lab8z2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.lab8z2.controller to javafx.fxml;
    exports org.example.lab8z2;
}