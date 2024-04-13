package com.example.la_sala_project.modelos;

public class ModeloDeuda {
    long id_deuda;
    long id_tutor;
    long id_hijo;
    String fecha_deuda;
    String hora_deuda;
    double monto_debido;
    double monto_debido_pagado;
    Boolean deuda_cumplida_sn;

    public ModeloDeuda() {
    }

    public ModeloDeuda(long id_deuda, long id_tutor, long id_hijo, String fecha_deuda, String hora_deuda, double monto_debido, double monto_debido_pagado, Boolean deuda_cumplida_sn) {
        this.id_deuda = id_deuda;
        this.id_tutor = id_tutor;
        this.id_hijo = id_hijo;
        this.fecha_deuda = fecha_deuda;
        this.hora_deuda = hora_deuda;
        this.monto_debido = monto_debido;
        this.monto_debido_pagado = monto_debido_pagado;
        this.deuda_cumplida_sn = deuda_cumplida_sn;
    }

    public long getId_deuda() {
        return id_deuda;
    }

    public void setId_deuda(long id_deuda) {
        this.id_deuda = id_deuda;
    }

    public long getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(long id_tutor) {
        this.id_tutor = id_tutor;
    }

    public long getId_hijo() {
        return id_hijo;
    }

    public void setId_hijo(long id_hijo) {
        this.id_hijo = id_hijo;
    }

    public String getFecha_deuda() {
        return fecha_deuda;
    }

    public void setFecha_deuda(String fecha_deuda) {
        this.fecha_deuda = fecha_deuda;
    }

    public String getHora_deuda() {
        return hora_deuda;
    }

    public void setHora_deuda(String hora_deuda) {
        this.hora_deuda = hora_deuda;
    }

    public double getMonto_debido() {
        return monto_debido;
    }

    public void setMonto_debido(double monto_debido) {
        this.monto_debido = monto_debido;
    }

    public double getMonto_debido_pagado() {
        return monto_debido_pagado;
    }

    public void setMonto_debido_pagado(double monto_debido_pagado) {
        this.monto_debido_pagado = monto_debido_pagado;
    }

    public Boolean getDeuda_cumplida_sn() {
        return deuda_cumplida_sn;
    }

    public void setDeuda_cumplida_sn(Boolean deuda_cumplida_sn) {
        this.deuda_cumplida_sn = deuda_cumplida_sn;
    }
}
