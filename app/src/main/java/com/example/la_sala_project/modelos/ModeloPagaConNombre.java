package com.example.la_sala_project.modelos;

public class ModeloPagaConNombre {
    private ModeloPaga paga;
    private String hijoNombre;
    private String hijoApellido;
    private String claseNombre;

    public ModeloPagaConNombre() {
    }

    public ModeloPagaConNombre(ModeloPaga paga, String hijoNombre, String hijoApellido, String claseNombre) {
        this.paga = paga;
        this.hijoNombre = hijoNombre;
        this.hijoApellido = hijoApellido;
        this.claseNombre = claseNombre;
    }

    public ModeloPaga getPaga() {
        return paga;
    }

    public void setPaga(ModeloPaga paga) {
        this.paga = paga;
    }

    public String getHijoNombre() {
        return hijoNombre;
    }

    public void setHijoNombre(String hijoNombre) {
        this.hijoNombre = hijoNombre;
    }

    public String getHijoApellido() {
        return hijoApellido;
    }

    public void setHijoApellido(String hijoApellido) {
        this.hijoApellido = hijoApellido;
    }

    public String getClaseNombre() {
        return claseNombre;
    }

    public void setClaseNombre(String claseNombre) {
        this.claseNombre = claseNombre;
    }
}
