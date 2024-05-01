package model;

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

}
