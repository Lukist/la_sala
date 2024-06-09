package com.example.la_sala_project.Alarma;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.la_sala_project.Database.DBhelper;
import com.example.la_sala_project.modelos.ModeloDeuda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AlarmReceiver extends BroadcastReceiver {
    DBhelper dBhelper;
    List<ModeloDeuda> listaDeUltimasDeudas;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        dBhelper = new DBhelper(context);

        Log.d("Alarma", "se activo la alarma");
        listaDeUltimasDeudas = dBhelper.traerListaDeUltimasDeudas();

        for (ModeloDeuda deuda : listaDeUltimasDeudas) {
            detectorDeDeudas(deuda.getFecha_deuda());
        }

        // Create a notification channel (required for Android Oreo and above)

    }

    private void detectorDeDeudas(String fecha) {

        Log.d("Fecha", "funcion llamada con exito");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Date date;
        try {
            date = sdf.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        Date currentDate = new Date();

        Log.d("Fecha", currentDate.toString());

        // Calculate the difference in milliseconds
        long differenceMillis =  currentDate.getTime() - date.getTime();

        // Convert milliseconds to days
        long differenceDays = TimeUnit.DAYS.convert(differenceMillis, TimeUnit.MILLISECONDS);

        Log.d("Fecha", String.valueOf(differenceDays));

        if (differenceDays >= 30) {
            Log.d("Fecha", "Se ha cumplido un mes desde la ultima deuda");
        } else {
            Log.d("Fecha", "Aun no ha pasado un mes desde la ultima deuda");
        }
    }
}
