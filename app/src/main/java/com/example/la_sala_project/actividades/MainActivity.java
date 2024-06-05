package com.example.la_sala_project.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.la_sala_project.Alarma.AlarmReceiver;
import com.example.la_sala_project.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView titulo;
    TextView subtitulo;

    Button btn_pagos, btn_deudas, btn_alumnos, btn_clases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titulo = findViewById(R.id.Main_Relative__Textview_Bienvenido);
        subtitulo = findViewById(R.id.Main_Relative__subtitulo);
        btn_deudas = findViewById(R.id.Main_Relative__button_deudores);
        btn_alumnos = findViewById(R.id.Main_Relative__button_alumnos);
        btn_clases = findViewById(R.id.Main_Relative__button_clases);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CabinSketch-Regular.ttf");
        titulo.setTypeface(typeface);
        subtitulo.setTypeface(typeface);

        setAlarm(getApplicationContext());

        btn_deudas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Deudas.class);
                startActivity(intent);
            }
        });

        btn_alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Alumnos.class);
                startActivity(intent);
            }
        });

        btn_clases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Clases.class);
                startActivity(intent);
            }
        });
    }

    public void setAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Set the alarm to trigger at 2:00 PM every day
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 2); // 2:00 PM
        calendar.set(Calendar.MINUTE, 40);
        calendar.set(Calendar.SECOND, 0);

        // Schedule the alarm to repeat every day
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    private void dateDifferenceChecker(String fecha) {

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
        long differenceMillis =  date.getTime() - currentDate.getTime();

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