package co.edu.uniquindio.grafosFinal.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ventanas/inicio.fxml"));

        primaryStage.setTitle("Inicio de sesión");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.show();
    }
}
