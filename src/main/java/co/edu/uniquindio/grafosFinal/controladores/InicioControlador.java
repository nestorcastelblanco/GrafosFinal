package co.edu.uniquindio.grafosFinal.controladores;

import co.edu.uniquindio.grafosFinal.modelo.Arista;
import co.edu.uniquindio.grafosFinal.modelo.Grafo;
import co.edu.uniquindio.grafosFinal.modelo.Matriz;
import co.edu.uniquindio.grafosFinal.modelo.Nodo;
<<<<<<< HEAD

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
=======
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
>>>>>>> parent of e67064e (Se actualizo la pagina principal)
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;

<<<<<<< HEAD
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class InicioControlador implements Initializable{
=======
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
>>>>>>> parent of e67064e (Se actualizo la pagina principal)

public class InicioControlador extends Application {
    private Grafo grafo;
<<<<<<< HEAD
    private Node contentOriginal;
    @FXML
    private Pane panelGrafo, panelContent;

    @FXML
    private Button btnCrearArista;

    @FXML
    private Button btnEliminarNodo;

    @FXML
    private Button btnEliminarArista;

    private HashMap<Arista, Double> mapaAngulo;

    private static InicioControlador inicioControlador;

    public static InicioControlador getInstance() {
        if (inicioControlador == null) {
            inicioControlador = new InicioControlador();
        }
        return inicioControlador;
    }
    @FXML
=======
    private Pane panelGrafo;
    private HashMap<Arista, Double> mapaAngulo;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        grafo = new Grafo();
        mapaAngulo = new HashMap<>();

        // Crear los botones en el borde izquierdo
        VBox cajaIzquierda = new VBox(10);
        cajaIzquierda.setAlignment(Pos.CENTER_LEFT);
        cajaIzquierda.setPadding(new Insets(10));
        Button botonMatrizRelacion = new Button("Matriz de Relación");
        Button botonIdentificarCondiciones = new Button("Grafo Conexo");
        Button botonTipo = new Button("Identificar Tipo");
        Button botonCircuitoEuleriano = new Button("Circuito Euleriano");
        establecerTamañoBoton(botonMatrizRelacion);
        establecerTamañoBoton(botonIdentificarCondiciones);
        establecerTamañoBoton(botonTipo);
        establecerTamañoBoton(botonCircuitoEuleriano);
        cajaIzquierda.getChildren().addAll(botonMatrizRelacion, botonIdentificarCondiciones, botonTipo, botonCircuitoEuleriano);

        // Crear los botones en el borde derecho
        VBox cajaDerecha = new VBox(10);
        cajaDerecha.setAlignment(Pos.CENTER_RIGHT);
        cajaDerecha.setPadding(new Insets(10));
        Button botonCircuitoHamiltoniano = new Button("Circuito Hamiltoniano");
        Button botonHamiltoniano = new Button("Grafo Hamiltoniano");
        Button botonAlgoritmoConectividad = new Button("Matriz de Adyacencia");
        establecerTamañoBoton(botonCircuitoHamiltoniano);
        establecerTamañoBoton(botonHamiltoniano);
        establecerTamañoBoton(botonAlgoritmoConectividad);
        cajaDerecha.getChildren().addAll(botonHamiltoniano, botonAlgoritmoConectividad, botonCircuitoHamiltoniano);

        // Crear el panel para dibujar el grafo
        panelGrafo = new Pane();
        panelGrafo.setStyle("-fx-background-color: white;");
        panelGrafo.setOnMouseClicked(event -> abrirDialogoAgregarNodo(event.getX(), event.getY()));

        // Crear botones de herramientas
        Button botonCrearArista = new Button("Crear Arista");
        Button botonEliminarNodo = new Button("Eliminar Nodo");
        Button botonEliminarArista = new Button("Eliminar Arista");

        // Manejadores de eventos para los botones de herramientas
        botonCrearArista.setOnAction(event -> abrirDialogoCrearArista());
        botonEliminarNodo.setOnAction(event -> abrirDialogoEliminarNodo());
        botonEliminarArista.setOnAction(event -> abrirDialogoEliminarArista());

        // Crear la barra de herramientas
        HBox barraHerramientas = new HBox(10);
        barraHerramientas.setAlignment(Pos.CENTER);
        barraHerramientas.setPadding(new Insets(10));
        barraHerramientas.getChildren().addAll(botonCrearArista, botonEliminarNodo, botonEliminarArista);

        // Crear el layout principal
        BorderPane raiz = new BorderPane();
        raiz.setLeft(cajaIzquierda);
        raiz.setRight(cajaDerecha);
        raiz.setCenter(panelGrafo);
        raiz.setBottom(barraHerramientas);

        // Crear la escena
        Scene scene = new Scene(raiz, 950, 600);

        // Configurar el escenario
        primaryStage.setScene(scene);
        primaryStage.setTitle("Aplicación de Grafo");
        primaryStage.show();

        botonMatrizRelacion.setOnAction(event -> Matriz.mostrarMatrizRelacion(grafo));
        botonIdentificarCondiciones.setOnAction(event -> grafoConexo());
        botonTipo.setOnAction(event -> identificarTipo());
        botonCircuitoEuleriano.setOnAction(event -> mostrarCircuitoEuleriano());
        botonCircuitoHamiltoniano.setOnAction(event -> mostrarCircuitoHamiltoniano());
        botonHamiltoniano.setOnAction(event -> identificarGrafoHamiltoniano());
        botonAlgoritmoConectividad.setOnAction(event -> mostrarMatrizAdyacencia());
    }

>>>>>>> parent of e67064e (Se actualizo la pagina principal)
    private void grafoConexo() {
        // Acción del botón "Grafo Conexo"
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Grafo Conexo");
        alerta.setHeaderText(null);
        boolean estado = grafo.isGraphConnected();
        String mensaje = grafo.getConexityJustification(estado);
        TextArea areaTexto = new TextArea();
        areaTexto.setEditable(false);
        areaTexto.setWrapText(true);
        areaTexto.setText(mensaje);
        alerta.getDialogPane().setContent(areaTexto);
        alerta.showAndWait();
    }

    private void identificarTipo() {
        // Acción del botón "Identificar Tipo"
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Tipo de Grafo");
        alerta.setHeaderText(null);
        String tipo = grafo.identificarTipo();
        TextArea areaTexto = new TextArea();
        areaTexto.setEditable(false);
        areaTexto.setWrapText(true);
        areaTexto.setText(tipo);
        alerta.getDialogPane().setContent(areaTexto);
        alerta.showAndWait();
    }

    private void mostrarCircuitoEuleriano() {
        // Acción del botón "Circuito Euleriano"
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Circuito Euleriano");
        alerta.setHeaderText(null);
        String circuito = grafo.getCircuitoEuleriano();
        TextArea areaTexto = new TextArea();
        areaTexto.setEditable(false);
        areaTexto.setWrapText(true);
        areaTexto.setText(circuito);
        alerta.getDialogPane().setContent(areaTexto);
        alerta.showAndWait();
    }

    private void mostrarCircuitoHamiltoniano() {
        // Acción del botón "Circuito Hamiltoniano"
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Circuito Hamiltoniano");
        alerta.setHeaderText(null);
        String circuito = grafo.getCircuitoHamiltoniano();
        TextArea areaTexto = new TextArea();
        areaTexto.setEditable(false);
        areaTexto.setWrapText(true);
        areaTexto.setText(circuito);
        alerta.getDialogPane().setContent(areaTexto);
        alerta.showAndWait();
    }

    private void identificarGrafoHamiltoniano() {
        // Acción del botón "Grafo Hamiltoniano"
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Grafo Hamiltoniano");
        alerta.setHeaderText(null);
        String justificacion = grafo.justificarGrafoHamiltoniano();
        TextArea areaTexto = new TextArea();
        areaTexto.setEditable(false);
        areaTexto.setWrapText(true);
        areaTexto.setText(justificacion);
        alerta.getDialogPane().setContent(areaTexto);
        alerta.showAndWait();
    }

    private void mostrarMatrizAdyacencia() {
        // Acción del botón "Matriz de Adyacencia"
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Matriz de Adyacencia");
        alerta.setHeaderText(null);
        String matriz = grafo.getMatrizAdyacencia();
        TextArea areaTexto = new TextArea();
        areaTexto.setEditable(false);
        areaTexto.setWrapText(true);
        areaTexto.setText(matriz);
        alerta.getDialogPane().setContent(areaTexto);
        alerta.showAndWait();
    }

    private void establecerTamañoBoton(Button boton) {
        // Establecer el tamaño máximo y mínimo del botón
        boton.setMinWidth(135);
        boton.setMaxWidth(135);
    }

    private void abrirDialogoAgregarNodo(double x, double y) {
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle("Agregar Nodo");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingrese el nombre del nodo:");

        Optional<String> resultado = dialogo.showAndWait();
        resultado.ifPresent(nombre -> {
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
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle("Crear Arista");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingrese los nombres de los nodos (separados por coma):");

        Optional<String> resultado = dialogo.showAndWait();
        resultado.ifPresent(input -> {
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
            double angulo = calcularAnguloUnico(arista);
            mapaAngulo.put(arista, angulo);

            // Dibujar la arista en el panel
            dibujarArista(arista, angulo);
        });
    }

    private double calcularAnguloUnico(Arista nuevaArista) {
        // Obtener las aristas ya existentes
        ArrayList<Arista> aristasExistentes = new ArrayList<>(grafo.getAristas());
        aristasExistentes.remove(nuevaArista); // Remover la nueva arista para evitar comparaciones innecesarias

        // Calcular ángulo único para la nueva arista
        double angulo = 0;
        while (true) {
            boolean anguloUnico = true;
            for (Arista arista : aristasExistentes) {
                double anguloExistente = mapaAngulo.get(arista);
                if (Math.abs(anguloExistente - angulo) < Math.PI / 6) {
                    anguloUnico = false;
                    break;
                }
            }
            if (anguloUnico) {
                break;
            }
            angulo += Math.PI / 6;
        }
        return angulo;
    }

    private void abrirDialogoEliminarNodo() {
        // Crear un diálogo para ingresar el nombre del nodo a eliminar
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle("Eliminar Nodo");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingrese el nombre del nodo a eliminar:");

        Optional<String> resultado = dialogo.showAndWait();
        resultado.ifPresent(nombre -> {
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
        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle("Eliminar Arista");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingrese los nombres de los nodos conectados por la arista (separados por coma):");

        Optional<String> resultado = dialogo.showAndWait();
        resultado.ifPresent(input -> {
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
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void dibujarNodo(Nodo nodo) {
        Circle circulo = new Circle(nodo.getX(), nodo.getY(), 20, Color.BLACK); // Nodo negro
        Label etiqueta = new Label(nodo.getNombre());
        etiqueta.setTextFill(Color.WHITE); // Texto blanco
        panelGrafo.getChildren().addAll(circulo, etiqueta);

        // Centrar el label en el círculo
        double anchoEtiqueta = etiqueta.getText().length() * 7; // Ajustar según el tamaño de la fuente
        double altoEtiqueta = 10; // Ajustar según el tamaño de la fuente

        etiqueta.setLayoutX(nodo.getX() - anchoEtiqueta / 2 - 2); // Ajuste de posición del texto
        etiqueta.setLayoutY(nodo.getY() - altoEtiqueta / 2 - 2); // Ajuste de posición del texto
    }

    private void dibujarArista(Arista arista, double angulo) {
        // Obtener las coordenadas del nodo
        double inicioX = arista.getNodoInicio().getX();
        double inicioY = arista.getNodoInicio().getY();
        double finX = arista.getNodoFin().getX();
        double finY = arista.getNodoFin().getY();

        double radio = 20; // Radio del círculo

        if (inicioX == finX && inicioY == finY) {
            // Dibujar un bucle (arista de A a A)
            Circle circulo = new Circle(inicioX, inicioY - radio, radio);
            circulo.setStroke(Color.BLACK); // Color negro para el bucle
            circulo.setFill(Color.TRANSPARENT);
            panelGrafo.getChildren().add(circulo);
        } else {
            // Calcular los puntos de control para la curva
            double controlX1 = inicioX + Math.cos(angulo) * 100;
            double controlY1 = inicioY + Math.sin(angulo) * 100;

            // Crear la arista curva entre los nodos de inicio y fin
            QuadCurve curva = new QuadCurve(inicioX, inicioY, controlX1, controlY1, finX, finY);
            curva.setStroke(Color.BLACK); // Color negro para la arista curva
            curva.setFill(null); // Rellenar la arista con color transparente

            // Agregar la arista al panel de dibujo
            panelGrafo.getChildren().add(curva);
        }
    }

    private void actualizarPanelDibujo() {
        // Limpiar el panel de dibujo
        panelGrafo.getChildren().clear();

        // Ordenar las aristas por ángulo
        ArrayList<Arista> aristasOrdenadas = new ArrayList<>(grafo.getAristas());
        aristasOrdenadas.sort((arista1, arista2) -> {
            double angulo1 = mapaAngulo.get(arista1);
            double angulo2 = mapaAngulo.get(arista2);
            return Double.compare(angulo1, angulo2);
        });

        // Volver a dibujar todas las aristas
        aristasOrdenadas.forEach(arista -> {
            double angulo = mapaAngulo.get(arista);
            dibujarArista(arista, angulo);
        });

        // Volver a dibujar todos los nodos
        grafo.getNodos().forEach(this::dibujarNodo);
    }
<<<<<<< HEAD

    public void mostrarMatrizRelacion(ActionEvent event) {
        contentOriginal = panelContent.getChildren().get(0);
        loadContent("/ventanas/MatrizRelacion.fxml");
        Matriz.mostrarMatrizRelacion(grafo);
    }

    public void cargarAnterior(){
        Node loader = panelContent.getChildren().get(0);
        
        // Obtiene la URL del archivo FXML cargado por el FXMLLoader
        String direccionFXML = loader.getLocation().toString();
    }
    private void loadContent(String fxmlPath){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node content = loader.load();

            panelContent.getChildren().clear();
            panelContent.getChildren().add(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void restaurarOriginal(){
        panelContent.getChildren().clear();
        panelContent.getChildren().add(contentOriginal);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grafo = new Grafo();
        mapaAngulo = new HashMap<>();
        contentOriginal = panelContent.getChildren().get(0);
    }
=======
>>>>>>> parent of e67064e (Se actualizo la pagina principal)
}
