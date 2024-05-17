package co.edu.uniquindio.grafosFinal.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CircuitoHamiltonianoController {

    @FXML
    private Label titulo;

    @FXML
    private Label texto;

    public void initialize() {
        titulo.setText("Circuto Hamiltoniano");
        texto.setText(
                "Un circuito hamiltoniano es un ciclo en un grafo que visita cada vértice exactamente una vez, excepto el nodo inicial que se repite al final. Este ciclo lleva el nombre del matemático William Rowan Hamilton y es un caso especial del problema del viajante. El objetivo es encontrar el camino más corto que visite todos los vértices de un grafo una vez y regrese al punto de partida.\n\n" +
                        "En matemáticas y teoría de grafos, los circuitos hamiltonianos son de gran interés debido a su complejidad computacional y su relación con otros problemas de optimización. Determinar si un grafo dado tiene un circuito hamiltoniano es un problema NP-completo, lo que significa que no existe un algoritmo eficiente conocido para resolverlo en todos los casos.\n\n" +
                        "En informática y ciencias de la computación, los circuitos hamiltonianos son utilizados en problemas de enrutamiento, diseño de circuitos, y en aplicaciones de logística y transporte. En la práctica, encontrar un circuito hamiltoniano puede requerir técnicas heurísticas o algoritmos aproximados debido a la complejidad del problema.\n\n" +
                        "En resumen, los circuitos hamiltonianos son una estructura importante en teoría de grafos con aplicaciones en diversas áreas, desde la planificación de rutas hasta el diseño de redes de comunicación."
        );
    }
}