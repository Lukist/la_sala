package com.example.la_sala_project.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.la_sala_project.R;

public class Clases extends AppCompatActivity {

    TextView titulo, crearClase, subtituloDivider;
    EditText nombreInput, precioInput;
    Button btn_ingresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases);

        titulo = findViewById(R.id.activity_clases__tv_titulo);
        crearClase = findViewById(R.id.activity_clases__tv_crear_clase);
        subtituloDivider = findViewById(R.id.activity_clases__tv_subtitulo_divider);
        nombreInput = findViewById(R.id.activity_clases__et_input_nombre);
        precioInput = findViewById(R.id.activity_clases__et_input_precio);
        btn_ingresar = findViewById(R.id.activity_clases__btn_ingresar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CabinSketch-Regular.ttf");
        titulo.setTypeface(typeface);
        crearClase.setTypeface(typeface);
        subtituloDivider.setTypeface(typeface);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}