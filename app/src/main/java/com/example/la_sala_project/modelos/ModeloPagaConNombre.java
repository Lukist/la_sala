package com.example.la_sala_project.modelos;

public class ModeloPagaConNombre {
    private ModeloPaga paga;
    private String tutorNombre;
    private String tutorApellido;

    public ModeloPagaConNombre() {
    }

    public ModeloPagaConNombre(ModeloPaga paga, String tutorNombre, String tutorApellido) {
        this.paga = paga;
        this.tutorNombre = tutorNombre;
        this.tutorApellido = tutorApellido;
    }

    public ModeloPaga getPaga() {
        return paga;
    }

    public void setPaga(ModeloPaga paga) {
        this.paga = paga;
    }

    public String getTutorNombre() {
        return tutorNombre;
    }

    public void setTutorNombre(String tutorNombre) {
        this.tutorNombre = tutorNombre;
    }

    public String getTutorApellido() {
        return tutorApellido;
    }

    public void setTutorApellido(String tutorApellido) {
        this.tutorApellido = tutorApellido;
    }
}
