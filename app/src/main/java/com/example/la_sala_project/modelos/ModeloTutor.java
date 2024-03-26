package com.example.la_sala_project.modelos;

public class ModeloTutor {

    long id_tutor;
    String nombre;
    String apellido;
    String correo;
    String dni;
    String nro_telefono;
    String telefono;
    String domicilio;
    Long id_hijo;

    public ModeloTutor() {
    }

    public ModeloTutor(long id_tutor, String nombre, String apellido, String correo, String dni, String nro_telefono, String telefono, String domicilio, long id_hijo) {
        this.id_tutor = id_tutor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.dni = dni;
        this.nro_telefono = nro_telefono;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.id_hijo = id_hijo;
    }

    public long getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(long id_tutor) {
        this.id_tutor = id_tutor;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNro_telefono() {
        return nro_telefono;
    }

    public void setNro_telefono(String nro_telefono) {
        this.nro_telefono = nro_telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public long getId_hijo() {
        return id_hijo;
    }

    public void setId_hijo(long id_hijo) {
        this.id_hijo = id_hijo;
    }
}
