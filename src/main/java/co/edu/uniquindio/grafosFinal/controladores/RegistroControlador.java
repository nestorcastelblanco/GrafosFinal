package co.edu.uniquindio.grafosFinal.controladores;

import co.edu.uniquindio.grafosFinal.exceptions.AtributoVacioException;
import co.edu.uniquindio.grafosFinal.exceptions.InformacionRepetidaException;
import co.edu.uniquindio.grafosFinal.modelo.GrafosFinal;
import co.edu.uniquindio.grafosFinal.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistroControlador {
    private final GrafosFinal grafosFinal = GrafosFinal.getInstance();
    @FXML
    private TextField identificacionField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField correoField;
    @FXML
    private TextField telefonoField;
    @FXML
    private Button registrarButton;
    @FXML
    private Button atrasButton;

    public void registrar(ActionEvent actionEvent) {
        try {
            Usuario usuario = grafosFinal.registrarUsuario(
                    identificacionField.getText(),
                    nombreField.getText(),
                    correoField.getText(),
                    telefonoField.getText()
            );

            mostrarMensaje(Alert.AlertType.INFORMATION, "El usuario " + nombreField.getText() + " ha sido registrado exitosamente");
        } catch (AtributoVacioException | InformacionRepetidaException e) {
            mostrarMensaje(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    public void atras(ActionEvent event) {
        Object evt = event.getSource();
        if (evt.equals(atrasButton)) {
            grafosFinal.loadStage("/ventanas/login.fxml", event);
        }
    }

    public void mostrarMensaje(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }
}
