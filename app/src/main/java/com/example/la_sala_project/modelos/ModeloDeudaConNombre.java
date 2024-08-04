package com.example.la_sala_project.modelos;

public class ModeloDeudaConNombre {
    private ModeloDeuda deuda;
    private String alumno_nombre;
    private String alumno_apellido;
    private String clase_nombre;

    public ModeloDeudaConNombre() {
    }

    public ModeloDeudaConNombre(ModeloDeuda deuda, String alumno_nombre, String alumno_apellido, String clase_nombre) {
        this.deuda = deuda;
        this.alumno_nombre = alumno_nombre;
        this.alumno_apellido = alumno_apellido;
        this.clase_nombre = clase_nombre;
    }

    public ModeloDeuda getDeuda() {
        return deuda;
    }

    public void setDeuda(ModeloDeuda deuda) {
        this.deuda = deuda;
    }

    public String getAlumno_nombre() {
        return alumno_nombre;
    }

    public void setAlumno_nombre(String alumno_nombre) {
        this.alumno_nombre = alumno_nombre;
    }

    public String getAlumno_apellido() {
        return alumno_apellido;
    }

    public void setAlumno_apellido(String alumno_apellido) {
        this.alumno_apellido = alumno_apellido;
    }

    public String getClase_nombre() {
        return clase_nombre;
    }

    public void setClase_nombre(String clase_nombre) {
        this.clase_nombre = clase_nombre;
    }
}
