package com.example.la_sala_project.modelos;

public class ModeloDeudaConNombre {
    private ModeloDeuda deuda;
    private String tutorName;

    public ModeloDeudaConNombre(ModeloDeuda deuda, String tutorName) {
        this.deuda = deuda;
        this.tutorName = tutorName;
    }

    public ModeloDeuda getDeuda() {
        return deuda;
    }

    public String getTutorName() {
        return tutorName;
    }
}
