package co.edu.uniquindio.grafosFinal.modelo;

public class Arista {

    private Nodo nodoInicio;
    private Nodo nodoFin;

    public Arista(Nodo nodoInicio, Nodo nodoFin) {
        this.nodoInicio = nodoInicio;
        this.nodoFin = nodoFin;
    }

    public Nodo getNodoInicio() {
        return nodoInicio;
    }

    public Nodo getNodoFin() {
        return nodoFin;
    }

    public boolean conectaCon(Nodo nodo, Nodo otroNodo) {
        // Verificar si esta arista conecta los nodos dados
        return (nodoInicio == nodo && nodoFin == otroNodo) || (nodoInicio == otroNodo && nodoFin == nodo);
    }
}
