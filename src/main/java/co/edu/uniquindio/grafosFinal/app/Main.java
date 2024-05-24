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
<<<<<<< HEAD
        Parent root = FXMLLoader.load(getClass().getResource("/ventanas/inicio.fxml"));

=======
        Parent root = FXMLLoader.load(getClass().getResource("/ventanas/login.fxml"));
>>>>>>> parent of e67064e (Se actualizo la pagina principal)
        primaryStage.setTitle("Inicio de sesión");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
