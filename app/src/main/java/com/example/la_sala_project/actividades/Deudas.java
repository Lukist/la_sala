package com.example.la_sala_project.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.la_sala_project.Database.DBhelper;
import com.example.la_sala_project.R;
import com.example.la_sala_project.adaptadores.DeudasAdapter;

public class Deudas extends AppCompatActivity {

    TextView titulo, subtitulo;
    ListView lista_pagados, lista_deudores;
    DBhelper db;
    DeudasAdapter deudasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deudas);

        titulo = findViewById(R.id.activity_deudas__tv_tittle);
        subtitulo = findViewById(R.id.activity_deudas__tv_subtitulo);
        lista_pagados = findViewById(R.id.activity_deudas__lv_lista_alumnos_dia);
        lista_deudores = findViewById(R.id.activity_deudas__lv_lista_deudores);

        db = new DBhelper(Deudas.this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CabinSketch-Regular.ttf");
        titulo.setTypeface(typeface);
        subtitulo.setTypeface(typeface);

        deudasAdapter = new DeudasAdapter(Deudas.this, R.layout.alumno_row, db, db.buscarDeudas());
        lista_deudores.setAdapter(deudasAdapter);
    }
}