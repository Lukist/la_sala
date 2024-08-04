package com.example.la_sala_project.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.la_sala_project.R;

/**
 * Esta actividad maneja la pantalla de login al inicio de lanzar la aplicacion
 * */
public class Login extends AppCompatActivity {

    /**
     * Creamos las variables
     * */
    Button btn_ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**
         * Le asignamos sus valores correspondientes
         * */
        btn_ingresar = findViewById(R.id.activity_login__btn_iniciar_sesion);


        /**
         * Seteamos los listeners correspondientes
         *
         * btnIngresar: enviamos a la pantalla de Inicio (Hub)
         * */
        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}