module org.example.kaskanej {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens org.example.kaskanej to javafx.fxml;
    exports org.example.kaskanej;
    exports org.example.kaskanej.model;
    opens org.example.kaskanej.model to javafx.fxml;
}