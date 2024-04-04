package com.example.la_sala_project.modelos;

public class ModeloClase {
    int id_clase;
    String nombre_clase;
    double precio;

    public ModeloClase() {
    }

    public ModeloClase(int id_clase, String nombre_clase, double precio) {
        this.id_clase = id_clase;
        this.nombre_clase = nombre_clase;
        this.precio = precio;
    }

    public int getId_clase() {
        return id_clase;
    }

    public void setId_clase(int id_clase) {
        this.id_clase = id_clase;
    }

    public String getNombre_clase() {
        return nombre_clase;
    }

    public void setNombre_clase(String nombre_clase) {
        this.nombre_clase = nombre_clase;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
