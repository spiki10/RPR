package org.example.lab8z2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.lab8z2.controller.PredmetController;
import org.example.lab8z2.model.PredmetModel;
import org.example.lab8z2.view.PredmetView;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("predmet.fxml"));

        PredmetModel model = new PredmetModel();
        PredmetView view = new PredmetView();

        PredmetController controller = new PredmetController(model, view);
        fxmlLoader.setController(controller);

        Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        stage.setTitle("Azuriraj Predmet");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}