package co.edu.uniquindio.grafosFinal.modelo;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

import java.util.List;

public class Matriz {
    public static void mostrarMatrizRelacion(Grafo grafo) {
        // Generar la matriz de relación del grafo
        int[][] matrizRelacion = generarMatrizRelacion(grafo);

        // Crear un TextArea para mostrar la matriz
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);

        // Construir la representación de la matriz como una cadena de texto
        StringBuilder cadenaMatriz = new StringBuilder();
        for (int[] fila : matrizRelacion) {
            for (int valor : fila) {
                cadenaMatriz.append(valor).append("\t");
            }
            cadenaMatriz.append("\n");
        }

        // Establecer la cadena de texto en el TextArea
        textArea.setText(cadenaMatriz.toString());

        // Mostrar la matriz en un diálogo emergente
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Matriz de Relación");
        alerta.setHeaderText(null);
        alerta.getDialogPane().setContent(textArea);
        alerta.showAndWait();
    }

    private static int[][] generarMatrizRelacion(Grafo grafo) {
        List<Nodo> nodos = grafo.getNodos();
        int n = nodos.size();
        int[][] matriz = new int[n][n];

        // Inicializar la matriz con ceros
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = 0;
            }
        }

        // Llenar la matriz según las aristas del grafo
        for (Arista arista : grafo.getAristas()) {
            Nodo nodoInicio = arista.getNodoInicio();
            Nodo nodoFin = arista.getNodoFin();
            int indiceInicio = nodos.indexOf(nodoInicio);
            int indiceFin = nodos.indexOf(nodoFin);
            matriz[indiceInicio][indiceFin] = 1;
            matriz[indiceFin][indiceInicio] = 1; // La matriz de relación es simétrica
        }

        return matriz;
    }
}
