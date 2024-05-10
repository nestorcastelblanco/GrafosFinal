package co.edu.uniquindio.grafosFinal.controladores;

import co.edu.uniquindio.grafosFinal.modelo.Arista;
import co.edu.uniquindio.grafosFinal.modelo.Grafo;
import co.edu.uniquindio.grafosFinal.modelo.Nodo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class PrincipalControlador implements Initializable {

    private Grafo grafo;
    @FXML
    private Pane panelGrafo;
    private HashMap<Arista, Double> angleMap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grafo = new Grafo();
        angleMap = new HashMap<>();

        // Crear botones
        Button createEdgeButton = new Button("Crear Arista");
        Button deleteNodeButton = new Button("Eliminar Nodo");
        Button deleteEdgeButton = new Button("Eliminar Arista");

        // Manejadores de eventos para los botones
        createEdgeButton.setOnAction(event -> abrirDialogoCrearArista());
        deleteNodeButton.setOnAction(event -> abrirDialogoEliminarNodo());
        deleteEdgeButton.setOnAction(event -> abrirDialogoEliminarArista());

        // Crear la barra de herramientas
        HBox toolbar = new HBox(10);
        toolbar.getChildren().addAll(createEdgeButton, deleteNodeButton, deleteEdgeButton);

        // Crear la ventana
        BorderPane root = new BorderPane();
        root.setCenter(panelGrafo);
        root.setBottom(toolbar);

        // Crear la escena
        Scene scene = new Scene(root, 800, 600);
    }

    private void abrirDialogoAgregarNodo(double x, double y) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Nodo");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese el nombre del nodo:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nombre -> {
            boolean nombreRepetido = grafo.getNodos().stream()
                    .anyMatch(nodo -> nodo.getNombre().equals(nombre));
            if (nombreRepetido) {
                mostrarAlerta("Error", "Ya existe un nodo con este nombre.");
                return;
            }

            Nodo nuevoNodo = new Nodo(nombre, x, y);
            grafo.agregarNodo(nuevoNodo);
            dibujarNodo(nuevoNodo);
        });
    }

    private void abrirDialogoCrearArista() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Crear Arista");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese los nombres de los nodos (separados por coma):");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(input -> {
            String[] nodos = input.split(",");
            if (nodos.length != 2) {
                mostrarAlerta("Error", "Debe ingresar exactamente dos nombres de nodos separados por coma.");
                return;
            }

            Nodo nodoInicio = grafo.getNodos().stream()
                    .filter(nodo -> nodo.getNombre().equals(nodos[0]))
                    .findFirst()
                    .orElse(null);
            Nodo nodoFin = grafo.getNodos().stream()
                    .filter(nodo -> nodo.getNombre().equals(nodos[1]))
                    .findFirst()
                    .orElse(null);

            if (nodoInicio == null || nodoFin == null) {
                mostrarAlerta("Error", "Uno o ambos nodos no existen.");
                return;
            }

            Arista arista = new Arista(nodoInicio, nodoFin);
            grafo.agregarArista(arista);

            double angle = calcularAnguloUnico(arista);
            angleMap.put(arista, angle);

            dibujarArista(arista, angle);
        });
    }

    private double calcularAnguloUnico(Arista nuevaArista) {
        ArrayList<Arista> aristasExistentes = new ArrayList<>(grafo.getAristas());
        aristasExistentes.remove(nuevaArista);

        double angle = 0;
        while (true) {
            boolean anguloUnico = true;
            for (Arista arista : aristasExistentes) {
                double existingAngle = angleMap.get(arista);
                if (Math.abs(existingAngle - angle) < Math.PI / 6) {
                    anguloUnico = false;
                    break;
                }
            }
            if (anguloUnico) {
                break;
            }
            angle += Math.PI / 6;
        }
        return angle;
    }

    private void abrirDialogoEliminarNodo() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar Nodo");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese el nombre del nodo a eliminar:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nombre -> {
            Nodo nodoAEliminar = grafo.getNodos().stream()
                    .filter(nodo -> nodo.getNombre().equals(nombre))
                    .findFirst()
                    .orElse(null);

            if (nodoAEliminar == null) {
                mostrarAlerta("Error", "El nodo no existe.");
                return;
            }

            grafo.getAristas().removeIf(arista ->
                    arista.getNodoInicio() == nodoAEliminar || arista.getNodoFin() == nodoAEliminar);
            grafo.getNodos().remove(nodoAEliminar);

            actualizarPanelDibujo();
        });
    }

    private void abrirDialogoEliminarArista() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar Arista");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese los nombres de los nodos conectados por la arista (separados por coma):");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(input -> {
            String[] nodos = input.split(",");
            if (nodos.length != 2) {
                mostrarAlerta("Error", "Debe ingresar exactamente dos nombres de nodos separados por coma.");
                return;
            }

            Nodo nodoInicio = grafo.getNodos().stream()
                    .filter(nodo -> nodo.getNombre().equals(nodos[0]))
                    .findFirst()
                    .orElse(null);
            Nodo nodoFin = grafo.getNodos().stream()
                    .filter(nodo -> nodo.getNombre().equals(nodos[1]))
                    .findFirst()
                    .orElse(null);

            if (nodoInicio == null || nodoFin == null) {
                mostrarAlerta("Error", "Uno o ambos nodos no existen.");
                return;
            }

            ArrayList<Arista> aristasAEliminar = new ArrayList<>();
            grafo.getAristas().forEach(arista -> {
                if ((arista.getNodoInicio() == nodoInicio && arista.getNodoFin() == nodoFin) ||
                        (arista.getNodoInicio() == nodoFin && arista.getNodoFin() == nodoInicio)) {
                    aristasAEliminar.add(arista);
                }
            });

            if (aristasAEliminar.isEmpty()) {
                mostrarAlerta("Error", "No existe una arista entre los nodos especificados.");
                return;
            }

            grafo.getAristas().removeAll(aristasAEliminar);
            actualizarPanelDibujo();
        });
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void dibujarNodo(Nodo nodo) {
        Circle circle = new Circle(nodo.getX(), nodo.getY(), 20, Color.BLACK);
        Label label = new Label(nodo.getNombre());
        label.setTextFill(Color.WHITE);
        panelGrafo.getChildren().addAll(circle, label);

        double labelWidth = label.getText().length() * 7;
        double labelHeight = 10;

        label.setLayoutX(nodo.getX() - labelWidth / 2 - 2);
        label.setLayoutY(nodo.getY() - labelHeight / 2 - 2);
    }

    private void dibujarArista(Arista arista, double angle) {
        double startX = arista.getNodoInicio().getX();
        double startY = arista.getNodoInicio().getY();
        double endX = arista.getNodoFin().getX();
        double endY = arista.getNodoFin().getY();

        double radius = 20;

        if (startX == endX && startY == endY) {
            Circle circle = new Circle(startX, startY - radius, radius);
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.TRANSPARENT);
            panelGrafo.getChildren().add(circle);
        } else {
            double controlX1 = startX + Math.cos(angle) * 100;
            double controlY1 = startY + Math.sin(angle) * 100;

            QuadCurve curva = new QuadCurve(startX, startY, controlX1, controlY1, endX, endY);
            curva.setStroke(Color.BLACK);
            curva.setFill(null);

            panelGrafo.getChildren().add(curva);
        }
    }

    private void actualizarPanelDibujo() {
        panelGrafo.getChildren().clear();

        ArrayList<Arista> aristasOrdenadas = new ArrayList<>(grafo.getAristas());
        aristasOrdenadas.sort((arista1, arista2) -> {
            double angle1 = angleMap.get(arista1);
            double angle2 = angleMap.get(arista2);
            return Double.compare(angle1, angle2);
        });

        for (Arista arista : aristasOrdenadas) {
            double angle = angleMap.get(arista);
            dibujarArista(arista, angle);
        }

        for (Nodo nodo : grafo.getNodos()) {
            dibujarNodo(nodo);
        }
    }
}
