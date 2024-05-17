package co.edu.uniquindio.grafosFinal.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CircuitoEulerianoController {

    @FXML
    private Label titulo;

    @FXML
    private Label texto;

    public void initialize() {
        titulo.setText("Circuito Euleriano");
        texto.setText(
                "Un circuito euleriano es un concepto fundamental en teoría de grafos con aplicaciones en diversos campos como matemáticas, informática y logística. \n\n" +
                        "En matemáticas, un circuito euleriano es un camino que recorre cada arista de un grafo exactamente una vez y regresa al nodo inicial. Esto implica que cada vértice del grafo es de grado par, ya que cada vez que se ingresa a un nodo, se sale de él.\n\n" +
                        "En informática, los circuitos eulerianos son utilizados en algoritmos de búsqueda y recorrido de grafos. Son especialmente útiles en la planificación de rutas óptimas en redes de transporte o en la optimización de rutas de entrega en logística.\n\n" +
                        "En aplicaciones prácticas, los circuitos eulerianos pueden ayudar a encontrar soluciones eficientes a problemas de optimización de recursos y planificación de rutas en una amplia gama de campos, desde el diseño de redes de comunicación hasta la logística en la distribución de bienes."
        );
    }
}