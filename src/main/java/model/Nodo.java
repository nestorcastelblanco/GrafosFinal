package model;

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
}
