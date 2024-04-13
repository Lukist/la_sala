package com.example.la_sala_project.modelos;

public class ModeloPagoDeuda {
    int id_pago_deuda;
    int id_pago;
    int id_deuda;
    double monto;

    public ModeloPagoDeuda() {
    }

    public ModeloPagoDeuda(int id_pago_deuda, int id_pago, int id_deuda, double monto) {
        this.id_pago_deuda = id_pago_deuda;
        this.id_pago = id_pago;
        this.id_deuda = id_deuda;
        this.monto = monto;
    }

    public int getId_pago_deuda() {
        return id_pago_deuda;
    }

    public void setId_pago_deuda(int id_pago_deuda) {
        this.id_pago_deuda = id_pago_deuda;
    }

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public int getId_deuda() {
        return id_deuda;
    }

    public void setId_deuda(int id_deuda) {
        this.id_deuda = id_deuda;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
