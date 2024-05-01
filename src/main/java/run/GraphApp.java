package run;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;
import model.Arista;
import model.Grafo;
import model.Nodo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class GraphApp extends Application {
    private Grafo grafo;
    private Pane graphPane;
    private HashMap<Arista, Double> angleMap;

    @Override
    public void start(Stage primaryStage) {
        grafo = new Grafo();
        angleMap = new HashMap<>();

        // Crear los botones en el borde izquierdo
        VBox leftBox = new VBox(10);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.setPadding(new Insets(10));
        Button recognizeMatrixButton = new Button("Matriz de Relación");
        Button identifyConditionsButton = new Button("Grafo conexo");
        Button differenceButton = new Button("Identificar Tipo");
        Button eulerianHamiltonianButton = new Button("Circuito Euleriano");
        setButtonSize(recognizeMatrixButton);
        setButtonSize(identifyConditionsButton);
        setButtonSize(differenceButton);
        setButtonSize(eulerianHamiltonianButton);
        leftBox.getChildren().addAll(recognizeMatrixButton, identifyConditionsButton,differenceButton,eulerianHamiltonianButton);

        // Crear los botones en el borde derecho
        VBox rightBox = new VBox(10);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        rightBox.setPadding(new Insets(10));
        Button eulerianHamiltonianButton1 = new Button("Circuito Hamiltoniano");
        Button hamiltonianButton = new Button("Grafo Hamiltoniano");
        Button connectivityAlgorithmButton = new Button("Matriz de Adyacencia");
        setButtonSize(eulerianHamiltonianButton1); // Establecer el mismo tamaño que el botón "Grafo Hamiltoniano"
        setButtonSize(hamiltonianButton); // Establecer el mismo tamaño que el botón "Grafo Hamiltoniano"
        setButtonSize(connectivityAlgorithmButton); // Establecer el mismo tamaño que el botón "Grafo Hamiltoniano"

        rightBox.getChildren().addAll(hamiltonianButton, connectivityAlgorithmButton, eulerianHamiltonianButton1);

        // Crear el panel para dibujar el grafo
        graphPane = new Pane();
        graphPane.setStyle("-fx-background-color: white;");
        graphPane.setOnMouseClicked(event -> abrirDialogoAgregarNodo(event.getX(), event.getY()));

        // Crear botones de herramientas
        Button createEdgeButton = new Button("Crear Arista");
        Button deleteNodeButton = new Button("Eliminar Nodo");
        Button deleteEdgeButton = new Button("Eliminar Arista");

        // Manejadores de eventos para los botones de herramientas
        createEdgeButton.setOnAction(event -> abrirDialogoCrearArista());
        deleteNodeButton.setOnAction(event -> abrirDialogoEliminarNodo());
        deleteEdgeButton.setOnAction(event -> abrirDialogoEliminarArista());

        // Crear la barra de herramientas
        HBox toolbar = new HBox(10);
        toolbar.setAlignment(Pos.CENTER);
        toolbar.setPadding(new Insets(10));
        toolbar.getChildren().addAll(createEdgeButton, deleteNodeButton, deleteEdgeButton);

        // Crear el layout principal
        BorderPane root = new BorderPane();
        root.setLeft(leftBox);
        root.setRight(rightBox);
        root.setCenter(graphPane);
        root.setBottom(toolbar);

        // Crear la escena
        Scene scene = new Scene(root, 950, 600);

        // Configurar el escenario
        primaryStage.setScene(scene);
        primaryStage.setTitle("Graph Application");
        primaryStage.show();
    }

    private void setButtonSize(Button button) {
        // Establecer el tamaño máximo y mínimo del botón
        button.setMinWidth(135);
        button.setMaxWidth(25);
    }

    private void abrirDialogoAgregarNodo(double x, double y) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Nodo");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese el nombre del nodo:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nombre -> {
            // Verificar si ya existe un nodo con el mismo nombre
            boolean nombreRepetido = grafo.getNodos().stream()
                    .anyMatch(nodo -> nodo.getNombre().equals(nombre));
            if (nombreRepetido) {
                mostrarAlerta("Error", "Ya existe un nodo con este nombre.");
                return;
            }

            // Crear el nodo y agregarlo al grafo
            Nodo nuevoNodo = new Nodo(nombre, x, y);
            grafo.agregarNodo(nuevoNodo);
            dibujarNodo(nuevoNodo);
        });
    }

    private void abrirDialogoCrearArista() {
        // Crear un diálogo para ingresar los nombres de los nodos
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

            // Buscar los nodos en el grafo por sus nombres
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

            // Crear la arista y agregarla al grafo
            Arista arista = new Arista(nodoInicio, nodoFin);
            grafo.agregarArista(arista);

            // Calcular ángulo único para la arista
            double angle = calcularAnguloUnico(arista);
            angleMap.put(arista, angle);

            // Dibujar la arista en el panel
            dibujarArista(arista, angle);
        });
    }

    private double calcularAnguloUnico(Arista nuevaArista) {
        // Obtener las aristas ya existentes
        ArrayList<Arista> aristasExistentes = new ArrayList<>(grafo.getAristas());
        aristasExistentes.remove(nuevaArista); // Remover la nueva arista para evitar comparaciones innecesarias

        // Calcular ángulo único para la nueva arista
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
        // Crear un diálogo para ingresar el nombre del nodo a eliminar
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar Nodo");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese el nombre del nodo a eliminar:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(nombre -> {
            // Buscar el nodo en el grafo por su nombre
            Nodo nodoAEliminar = grafo.getNodos().stream()
                    .filter(nodo -> nodo.getNombre().equals(nombre))
                    .findFirst()
                    .orElse(null);

            if (nodoAEliminar == null) {
                mostrarAlerta("Error", "El nodo no existe.");
                return;
            }

            // Eliminar el nodo y sus aristas asociadas
            grafo.getAristas().removeIf(arista ->
                    arista.getNodoInicio() == nodoAEliminar || arista.getNodoFin() == nodoAEliminar);
            grafo.getNodos().remove(nodoAEliminar);

            // Actualizar el panel de dibujo
            actualizarPanelDibujo();
        });
    }

    private void abrirDialogoEliminarArista() {
        // Crear un diálogo para ingresar los nombres de los nodos que están conectados por la arista a eliminar
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

            // Buscar los nodos en el grafo por sus nombres
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

            // Buscar la arista que conecta los nodos
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

            // Eliminar la arista del grafo y actualizar el panel de dibujo
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
        Circle circle = new Circle(nodo.getX(), nodo.getY(), 20, Color.BLACK); // Nodo negro
        Label label = new Label(nodo.getNombre());
        label.setTextFill(Color.WHITE); // Texto blanco
        graphPane.getChildren().addAll(circle, label);

        // Centrar el label en el círculo
        double labelWidth = label.getText().length() * 7; // Ajustar según el tamaño de la fuente
        double labelHeight = 10; // Ajustar según el tamaño de la fuente

        label.setLayoutX(nodo.getX() - labelWidth / 2 - 2); // Ajuste de posición del texto
        label.setLayoutY(nodo.getY() - labelHeight / 2 - 2); // Ajuste de posición del texto
    }

    private void dibujarArista(Arista arista, double angle) {
        // Obtener las coordenadas del nodo
        double startX = arista.getNodoInicio().getX();
        double startY = arista.getNodoInicio().getY();
        double endX = arista.getNodoFin().getX();
        double endY = arista.getNodoFin().getY();

        double radius = 20; // Radio del círculo

        if (startX == endX && startY == endY) {
            // Dibujar un bucle (arista de A a A)
            Circle circle = new Circle(startX, startY - radius, radius);
            circle.setStroke(Color.BLACK); // Color negro para el bucle
            circle.setFill(Color.TRANSPARENT);
            graphPane.getChildren().add(circle);
        } else {
            // Calcular los puntos de control para la curva
            double controlX1 = startX + Math.cos(angle) * 100;
            double controlY1 = startY + Math.sin(angle) * 100;

            // Crear la arista curva entre los nodos de inicio y fin
            QuadCurve curva = new QuadCurve(startX, startY, controlX1, controlY1, endX, endY);
            curva.setStroke(Color.BLACK); // Color negro para la arista curva
            curva.setFill(null); // Rellenar la arista con color transparente

            // Agregar la arista al panel de dibujo
            graphPane.getChildren().add(curva);
        }
    }

    private void actualizarPanelDibujo() {
        // Limpiar el panel de dibujo
        graphPane.getChildren().clear();

        // Ordenar las aristas por ángulo
        ArrayList<Arista> aristasOrdenadas = new ArrayList<>(grafo.getAristas());
        aristasOrdenadas.sort((arista1, arista2) -> {
            double angle1 = angleMap.get(arista1);
            double angle2 = angleMap.get(arista2);
            return Double.compare(angle1, angle2);
        });

        // Volver a dibujar todas las aristas en el orden ordenado
        for (Arista arista : aristasOrdenadas) {
            double angle = angleMap.get(arista);
            dibujarArista(arista, angle);
        }

        // Volver a dibujar todos los nodos
        for (Nodo nodo : grafo.getNodos()) {
            dibujarNodo(nodo);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
