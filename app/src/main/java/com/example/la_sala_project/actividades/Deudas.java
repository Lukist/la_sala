package com.example.la_sala_project.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.la_sala_project.Database.DBhelper;
import com.example.la_sala_project.R;
import com.example.la_sala_project.adaptadores.DeudasAdapter;
import com.example.la_sala_project.adaptadores.PagosAdapter;

public class Deudas extends AppCompatActivity {

    TextView titulo, subtitulo;
    ListView lista_pagados, lista_deudores;
    DBhelper db;
    DeudasAdapter deudasAdapter;
    PagosAdapter pagosAdapter;
    ImageButton imgbtn_visibilidadDeuda, imgbtn_visibilidadPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deudas);

        titulo = findViewById(R.id.activity_deudas__tv_tittle);
        subtitulo = findViewById(R.id.activity_deudas__tv_subtitulo);
        lista_pagados = findViewById(R.id.activity_deudas__lv_lista_alumnos_dia);
        lista_deudores = findViewById(R.id.activity_deudas__lv_lista_deudores);
        imgbtn_visibilidadPago = findViewById(R.id.activity_deudas__imgbtn_visibilidadPagos);
        imgbtn_visibilidadDeuda = findViewById(R.id.activity_deudas__imgbtn_visibilidadDeudas);

        db = new DBhelper(Deudas.this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CabinSketch-Regular.ttf");
        titulo.setTypeface(typeface);
        subtitulo.setTypeface(typeface);

        pagosAdapter = new PagosAdapter(Deudas.this, R.layout.alumno_row, db.buscarPagos());
        lista_pagados.setAdapter(pagosAdapter);

        deudasAdapter = new DeudasAdapter(Deudas.this, R.layout.alumno_row, db, db.buscarDeudas());
        lista_deudores.setAdapter(deudasAdapter);

        imgbtn_visibilidadDeuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lista_deudores.getVisibility() == View.VISIBLE) {
                    lista_deudores.setVisibility(View.GONE);
                }else {
                    lista_deudores.setVisibility(View.VISIBLE);
                }
            }
        });

        imgbtn_visibilidadPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lista_pagados.getVisibility() == View.VISIBLE) {
                    lista_pagados.setVisibility(View.GONE);
                }else {
                    lista_pagados.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}