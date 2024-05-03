package com.example.la_sala_project.modelos;

public class ModeloAlumno {
    long idAlumno;
    String nombre;
    String apellido;

    public ModeloAlumno() {
    }

    public ModeloAlumno(long idAlumno, String nombre, String apellido) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
