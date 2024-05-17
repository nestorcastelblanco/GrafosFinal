package co.edu.uniquindio.grafosFinal.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TipoGrafoController {
    @FXML
    private Label titulo;

    @FXML
    private Label texto;

    public void initialize() {
        titulo.setText("Tipos de Grafos");
        texto.setText(
                "En teoría de grafos, los tipos de grafos se clasifican según sus propiedades estructurales y características topológicas. Algunos de los tipos de grafos más comunes incluyen:\n\n" +

                        "1. Grafo Simple: Es un grafo sin bucles ni múltiples aristas entre los mismos dos nodos.\n\n" +

                        "2. Grafo Dirigido: También conocido como digrafo, es un grafo donde las aristas tienen dirección. Es decir, se puede viajar de un nodo a otro siguiendo la dirección de las aristas.\n\n" +

                        "3. Grafo Ponderado: Es un grafo donde cada arista tiene asignado un peso o valor numérico.\n\n" +

                        "4. Grafo Conexo: Es un grafo donde existe al menos un camino entre cada par de nodos. No hay nodos aislados.\n\n" +

                        "5. Grafo Completo: Es un grafo donde cada par de nodos está conectado por una arista. Es decir, todos los nodos están directamente conectados entre sí.\n\n" +

                        "6. Grafo Bipartito: Es un grafo cuyos vértices pueden dividirse en dos conjuntos disjuntos, de tal manera que cada arista conecta un vértice de un conjunto con un vértice del otro conjunto.\n\n" +

                        "7. Grafo Euleriano: Es un grafo donde existe un ciclo que visita cada arista exactamente una vez.\n\n" +

                        "8. Grafo Hamiltoniano: Es un grafo donde existe un ciclo que visita cada vértice exactamente una vez.\n\n" +

                        "Estos son solo algunos ejemplos de los tipos de grafos más estudiados en teoría de grafos, pero hay muchos otros tipos y variantes que se utilizan en diversas aplicaciones y problemas de optimización."
        );
    }
}
