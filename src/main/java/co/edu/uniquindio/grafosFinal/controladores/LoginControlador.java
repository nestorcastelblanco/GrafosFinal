package co.edu.uniquindio.grafosFinal.controladores;

import co.edu.uniquindio.grafosFinal.modelo.GrafosFinal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginControlador {
    private final GrafosFinal grafosFinal = GrafosFinal.getInstance();
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button infoButton;

    public void login(ActionEvent event) {
        Object evt = event.getSource();
        if (evt.equals(loginButton)) {
            String username = usernameField.getText();
            String contrasena = passwordField.getText();

            if (grafosFinal.verificarCredenciales(contrasena, username)) {
                // Las credenciales son válidas, puedes cargar la siguiente ventana
                grafosFinal.loadStage("/ventanas/inicio.fxml", event);
                mostrarMensaje(Alert.AlertType.INFORMATION, "El usuario " + username + " ha ingresado correctamente");

            } else {
                // Las credenciales son inválidas
                mostrarMensaje(Alert.AlertType.ERROR, "El usuario no esta registrado");
            }
        }
    }

    public void register(ActionEvent event) {
        Object evt = event.getSource();
        if (evt.equals(registerButton)) {
            grafosFinal.loadStage("/ventanas/registro.fxml", event);
        }
    }

    public void info(ActionEvent event) {
        Object evt = event.getSource();
        if (evt.equals(infoButton)) {
            grafosFinal.loadStage("/ventanas/info.fxml", event);
        }
    }


    public void mostrarMensaje(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }
}
