package co.edu.uniquindio.grafosFinal.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MatrizAdyacenciaController {

    @FXML
    private Label titulo;

    @FXML
    private Label texto;

    public void initialize() {
        titulo.setText("Matriz de Adyacencia");
        texto.setText(
                "Una matriz de adyacencia es una representación tabular de un grafo en la cual cada fila y columna corresponden a los vértices del grafo, y cada celda indica si existe una arista entre los vértices correspondientes. Generalmente, se utiliza una matriz booleana donde el valor 'true' indica la presencia de una arista y 'false' indica la ausencia de la misma.\n\n" +
                        "En matemáticas y teoría de grafos, las matrices de adyacencia son una herramienta común para visualizar y analizar la conectividad entre los vértices de un grafo. Son útiles para determinar rápidamente si dos nodos están conectados y para calcular diversas propiedades del grafo, como el grado de un vértice o la existencia de ciclos.\n\n" +
                        "En informática, las matrices de adyacencia se utilizan en algoritmos de búsqueda, recorrido de grafos y en la representación de estructuras de datos para grafos. Aunque ocupan más espacio en memoria que otras representaciones como listas de adyacencia, son eficientes para determinar la presencia de aristas entre pares de nodos.\n\n" +
                        "En resumen, las matrices de adyacencia son una forma compacta y eficiente de representar la estructura de un grafo, facilitando su análisis y manipulación en una variedad de aplicaciones."
        );
    }
}