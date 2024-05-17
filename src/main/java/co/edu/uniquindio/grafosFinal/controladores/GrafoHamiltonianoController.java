package co.edu.uniquindio.grafosFinal.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GrafoHamiltonianoController {

    @FXML
    private Label titulo;

    @FXML
    private Label texto;

    public void initialize() {
        titulo.setText("Grafo Hamiltoniano");
        texto.setText(
                "Un grafo hamiltoniano es un tipo de grafo en el cual existe un ciclo que visita cada vértice exactamente una vez, con excepción del vértice de partida que se repite al final. Este ciclo se llama ciclo hamiltoniano, y es análogo al circuito euleriano pero visita todos los vértices en lugar de todas las aristas.\n\n" +
                        "En matemáticas y teoría de grafos, los grafos hamiltonianos son de gran interés debido a su relación con el problema del viajante, que consiste en encontrar el camino más corto que visite todas las ciudades de un conjunto exactamente una vez y regrese al punto de partida. En muchos casos prácticos, encontrar un ciclo hamiltoniano en un grafo puede ser NP-completo, lo que significa que no existe un algoritmo eficiente para resolver el problema.\n\n" +
                        "En informática, los grafos hamiltonianos son utilizados en problemas de optimización combinatoria, como en la planificación de rutas de entrega, en el diseño de circuitos integrados y en la programación de robots para recorrer todos los puntos de un área sin pasar dos veces por el mismo lugar.\n\n" +
                        "En resumen, los grafos hamiltonianos representan una estructura de datos y un concepto algorítmico importante con diversas aplicaciones en matemáticas, informática y áreas relacionadas."
        );
    }
}