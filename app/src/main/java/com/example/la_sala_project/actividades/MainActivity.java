package com.example.la_sala_project.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.la_sala_project.R;

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
}