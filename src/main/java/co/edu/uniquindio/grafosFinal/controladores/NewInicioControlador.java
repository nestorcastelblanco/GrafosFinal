package co.edu.uniquindio.grafosFinal.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NewInicioControlador {

    @FXML
    private Pane contentPane;

    @FXML
    private void handleButton1() {
        loadContent("/ventanas/content1.fxml");
    }

    @FXML
    private void handleButton2() {
        loadContent("/ventanas/content2.fxml");
    }

    private void loadContent(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane newPane = loader.load();
            contentPane.getChildren().setAll(newPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
