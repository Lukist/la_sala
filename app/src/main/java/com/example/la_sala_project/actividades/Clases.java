package com.example.la_sala_project.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.example.la_sala_project.R;

public class Clases extends AppCompatActivity {

    TextView titulo, crearClase, subtituloDivider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);

        titulo = findViewById(R.id.activity_clases__tv_titulo);
        crearClase = findViewById(R.id.activity_clases__tv_crear_clase);
        subtituloDivider = findViewById(R.id.activity_clases__tv_subtitulo_divider);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CabinSketch-Regular.ttf");
        titulo.setTypeface(typeface);
        crearClase.setTypeface(typeface);
        subtituloDivider.setTypeface(typeface);
    }
}