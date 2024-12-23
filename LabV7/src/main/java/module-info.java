module org.example.labv7 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.xml;

    opens org.example.labv7 to javafx.fxml;
    exports org.example.labv7;
}