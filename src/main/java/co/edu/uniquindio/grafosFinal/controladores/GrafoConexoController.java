package co.edu.uniquindio.grafosFinal.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GrafoConexoController {

    @FXML
    private Label titulo;

    @FXML
    private Label texto;

    public void initialize() {
        titulo.setText("Grafo Conexo");
        texto.setText(
                "Un grafo conexo es un tipo de grafo en el cual existe al menos un camino entre cada par de nodos. En otras palabras, no hay nodos aislados y todos están conectados de alguna manera.\n\n" +
                        "En matemáticas y teoría de grafos, los grafos conexos son de gran importancia debido a su capacidad para modelar situaciones donde la conectividad es fundamental. Por ejemplo, en redes de comunicación, sistemas de transporte o circuitos eléctricos, la existencia de una conexión entre todos los nodos es esencial para el funcionamiento eficiente del sistema.\n\n" +
                        "En informática, los grafos conexos son utilizados en algoritmos de búsqueda, en la detección de componentes conectados en redes y en la planificación de rutas óptimas en aplicaciones logísticas y de transporte.\n\n" +
                        "En resumen, los grafos conexos son una herramienta poderosa para modelar y resolver problemas que involucran la conectividad entre entidades o nodos en un sistema."
        );
    }
}