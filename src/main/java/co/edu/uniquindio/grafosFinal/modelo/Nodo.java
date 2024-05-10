package co.edu.uniquindio.grafosFinal.modelo;

public class Nodo {
    private String nombre;
    private double x;
    private double y;

    public Nodo(String nombre, double x, double y) {
        this.nombre = nombre;
        this.x = x;
        this.y = y;
    }

    public String getNombre() {
        return nombre;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean tieneAristaCon(Nodo otroNodo, Grafo grafo) {
        // Verificar si hay una arista entre este nodo y otro nodo
        for (Arista arista : grafo.getAristas()) {
            if (arista.conectaCon(this, otroNodo)) {
                return true;
            }
        }
        return false;
    }

    public int getGrado(Grafo grafo) {
        // Calcular el grado del nodo
        int grado = 0;
        for (Arista arista : grafo.getAristas()) {
            if (arista.getNodoInicio() == this || arista.getNodoFin() == this) {
                grado++;
            }
        }
        return grado;
    }

    public boolean esAdyacente(Nodo otroNodo, Grafo grafo) {
        // Verificar si este nodo es adyacente al nodo dado
        return this.tieneAristaCon(otroNodo, grafo);
    }
}
