package co.edu.uniquindio.grafosFinal.controladores;

import co.edu.uniquindio.grafosFinal.modelo.GrafosFinal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InfoControlador {

    private final GrafosFinal grafosFinal = GrafosFinal.getInstance();
    @FXML
    private Button atrasButton;

    public void atras(ActionEvent event) {
        Object evt = event.getSource();
        if (evt.equals(atrasButton)) {
            grafosFinal.loadStage("/ventanas/login.fxml", event);
        }
    }


}
