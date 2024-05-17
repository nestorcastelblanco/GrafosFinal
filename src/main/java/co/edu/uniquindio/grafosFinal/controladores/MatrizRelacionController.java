package co.edu.uniquindio.grafosFinal.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MatrizRelacionController {

    @FXML
    private Label titulo;

    @FXML
    private Label texto;
    @FXML
    private Button volver;

    private InicioControlador inicioControlador;

    public void initialize() {
        titulo.setText("Matriz de Relación");
        texto.setText(
                "Una matriz de relación es una herramienta fundamental en diversos campos como la matemática, la informática, las ciencias sociales y más. \n\n" +
                        "En matemáticas, una matriz de relación es una matriz cuadrada que describe las relaciones entre elementos de un conjunto. Cada entrada en la matriz indica si un par de elementos están relacionados de alguna manera. Por ejemplo, si los elementos representan nodos de un grafo, una entrada distinta de cero indica que hay una arista entre los nodos correspondientes.\n\n" +
                        "En informática, las matrices de relación se utilizan en algoritmos de búsqueda, recorrido de grafos y en la representación de bases de datos relacionales.\n\n" +
                        "En ciencias sociales, las matrices de relación pueden utilizarse para modelar interacciones entre individuos, grupos o entidades en una red social o en un sistema de análisis de preferencias."
        );
    }

    public void setInicioControlador (InicioControlador inicioControlador){
        this.inicioControlador = inicioControlador;
    }

    @FXML
    public void volver(ActionEvent actionEvent) {
        inicioControlador.restaurarOriginal();
    }
}