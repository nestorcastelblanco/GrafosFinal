package co.edu.uniquindio.grafosFinal.modelo;

import co.edu.uniquindio.grafosFinal.exceptions.AtributoVacioException;
import co.edu.uniquindio.grafosFinal.exceptions.InformacionRepetidaException;
import co.edu.uniquindio.grafosFinal.utils.ArchivoUtils;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

@Log
public class GrafosFinal {

    /**
     * Ruta del archivo que almacena los datos de los usuarios.
     */
    private static final String RUTA_USUARIOS = "src/main/resources/serializable/usuarios.ser";

    /**
     * Instancia única de la clase GrafosFinal.
     */
    private static GrafosFinal grafosFinal;

    /**
     * ArrayList que almacena los usuarios registrados en la aplicación.
     */
    private ArrayList<Usuario> usuarios;

    private GrafosFinal() {
        inicializarLogger();
        log.info("Se crea una nueva instancia de la aplicacion");

        this.usuarios = new ArrayList<>();
        leerUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    /**
     * Método que devuelve la instancia única de la clase GrafosFinal (patrón Singleton).
     *
     * @return La instancia única de la clase GrafosFinal.
     */
    public static GrafosFinal getInstance() {
        if (grafosFinal == null) {
            grafosFinal = new GrafosFinal();
        }
        return grafosFinal;
    }

    private void inicializarLogger() {
        try {
            FileHandler fh = new FileHandler("logs.log", true);
            fh.setFormatter(new SimpleFormatter());
            log.addHandler(fh);
        } catch (IOException e) {
            log.severe(e.getMessage());
        }
    }

    public Usuario registrarUsuario(String identificacion, String nombre, String correo, String telefono) throws AtributoVacioException, InformacionRepetidaException {

        if (identificacion == null || identificacion.isBlank()) {
            throw new AtributoVacioException("La identificación es obligatoria");
        }

        if (obtenerUsuario(identificacion) != null) {
            throw new InformacionRepetidaException("La identificación " + identificacion + " ya está registrada");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new AtributoVacioException("El nombre es obligatorio");
        }

        if (correo == null || correo.isBlank()) {
            throw new AtributoVacioException("El correo es obligatorio");
        }

        if (obtenerCorreo(correo) != null) {
            throw new InformacionRepetidaException("El correo " + correo + " ya está registrado");
        }

        if (telefono == null || telefono.isBlank()) {
            throw new AtributoVacioException("El teléfono es obligatorio");
        }

        Usuario usuario = new Usuario(identificacion, nombre, correo, telefono);

        usuarios.add(usuario);

        // Aquí guardamos el usuario en una base de datos o en algún otro medio de almacenamiento
        escribirUsuario();

        log.info("Se ha registrado un nuevo usuario con identificacion: " + identificacion);

        return usuario;
    }

    private void escribirUsuario() {
        try {
            ArchivoUtils.serializarObjeto(RUTA_USUARIOS, usuarios);
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }

    // Método para obtener un usuario por su identificación
    public Usuario obtenerUsuario(String identificacion) {
        for (Usuario usuario : usuarios) {
            if (usuario.getIdentificacion().equals(identificacion)) {
                return usuario;
            }
        }
        return null; // Retorna null si no se encuentra el usuario con la identificación dada
    }

    // Método para obtener un usuario por su correo electrónico
    public Usuario obtenerCorreo(String correo) {
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return usuario;
            }
        }
        return null; // Retorna null si no se encuentra el usuario con el correo electrónico dado
    }

    private void leerUsuarios() {
        try {
            ArrayList<Usuario> lista = (ArrayList<Usuario>) ArchivoUtils.deserializarObjeto(RUTA_USUARIOS);
            if (lista != null) {
                this.usuarios = lista;
            }
        } catch (IOException | ClassNotFoundException e) {
            log.severe(e.getMessage());
        }
    }

    public boolean verificarCredenciales(String identificacion, String nombre) {
        for (Usuario usuario : usuarios) {
            if (identificacion.equals(usuario.getIdentificacion()) && nombre.equals(usuario.getNombre())) {
                return true; // Retorna true si se encuentra un usuario con la identificación y nombre dados
            }
        }
        return false; // Retorna false si no se encuentra ningún usuario con los datos dados
    }

    public void loadStage(String url, Event event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(Objects.requireNonNull(GrafosFinal.class.getResource(url)));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("Teoría de Grafos");
            newStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
