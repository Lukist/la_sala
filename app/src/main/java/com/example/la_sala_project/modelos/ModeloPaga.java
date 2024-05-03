package com.example.la_sala_project.modelos;

public class ModeloPaga {
    long id_recibo;
    long id_tutor;
    long id_hijo;
    long id_clase;
    String fecha_pago;
    String hora_pago;
    double monto_pagado;

    public ModeloPaga() {
    }

    public ModeloPaga(long id_recibo, long id_tutor, long id_hijo, long id_clase, String fecha_pago, String hora_pago, double monto_pagado) {
        this.id_recibo = id_recibo;
        this.id_tutor = id_tutor;
        this.id_hijo = id_hijo;
        this.id_clase = id_clase;
        this.fecha_pago = fecha_pago;
        this.hora_pago = hora_pago;
        this.monto_pagado = monto_pagado;
    }

    public long getId_recibo() {
        return id_recibo;
    }

    public void setId_recibo(long id_recibo) {
        this.id_recibo = id_recibo;
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

    public long getId_clase() {
        return id_clase;
    }

    public void setId_clase(long id_clase) {
        this.id_clase = id_clase;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getHora_pago() {
        return hora_pago;
    }

    public void setHora_pago(String hora_pago) {
        this.hora_pago = hora_pago;
    }

    public double getMonto_pagado() {
        return monto_pagado;
    }

    public void setMonto_pagado(double monto_pagado) {
        this.monto_pagado = monto_pagado;
    }
}
