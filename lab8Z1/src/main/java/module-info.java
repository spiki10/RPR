module org.example.lab8 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.xml;

    opens org.example.lab8 to javafx.fxml;
    opens org.example.lab8.controller to javafx.fxml;
    opens org.example.lab8.model to javafx.fxml;
    opens org.example.lab8.view to javafx.fxml;
    exports org.example.lab8;
}