package com.example.la_sala_project.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.la_sala_project.Database.DBhelper;
import com.example.la_sala_project.R;
import com.example.la_sala_project.adaptadores.DeudasAdapter;
import com.example.la_sala_project.adaptadores.PagosAdapter;
import com.example.la_sala_project.modelos.ModeloDeuda;
import com.example.la_sala_project.modelos.ModeloPaga;
import com.example.la_sala_project.modelos.ModeloPagaConNombre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Deudas extends AppCompatActivity {

    TextView titulo, subtitulo;
    ListView lista_pagados, lista_deudores;
    DBhelper db;
    DeudasAdapter deudasAdapter;
    PagosAdapter pagosAdapter;
    ImageButton imgbtn_visibilidadDeuda, imgbtn_visibilidadPago;
    EditText buscadorPagos, buscadorDeudas;
    List<ModeloPagaConNombre> listaDePagos, fitredList;
    List<ModeloDeuda> listaDeDeudas, filtrosDeuda;
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
        buscadorPagos = findViewById(R.id.activity_deudas__input_buscadorPagos);
        buscadorDeudas = findViewById(R.id.activity_deudas__input_buscadorDeudas);


        db = new DBhelper(Deudas.this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CabinSketch-Regular.ttf");
        titulo.setTypeface(typeface);
        subtitulo.setTypeface(typeface);

        listaDePagos = db.buscarPagos();
        listaDeDeudas = db.buscarDeudas();

        fitredList = new ArrayList<>(listaDePagos);


        pagosAdapter = new PagosAdapter(Deudas.this, R.layout.alumno_row, fitredList);
        lista_pagados.setAdapter(pagosAdapter);

        deudasAdapter = new DeudasAdapter(Deudas.this, R.layout.alumno_row, db, listaDeDeudas);
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

        buscadorPagos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterList(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void filterList(String query) {
        fitredList.clear();

        if (query.isEmpty()) {
            fitredList.addAll(listaDePagos);
        }else {
            String lowerCaseQuery = query.toLowerCase(); // Convert query to lowercase for case-insensitive search

            for (ModeloPagaConNombre paga : db.buscarPagos()) {
                if (paga.getTutorNombre().toLowerCase().contains(lowerCaseQuery) || paga.getTutorApellido().toLowerCase().contains(lowerCaseQuery)) {
                    fitredList.add(paga);
                }
            }
        }
        pagosAdapter.notifyDataSetChanged();
    }


}