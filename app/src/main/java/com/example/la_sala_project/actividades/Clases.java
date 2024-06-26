package com.example.la_sala_project.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.la_sala_project.Database.DBhelper;
import com.example.la_sala_project.R;
import com.example.la_sala_project.adaptadores.ClasesAdapter;
import com.example.la_sala_project.modelos.ModeloClase;

public class Clases extends AppCompatActivity {

    TextView titulo, crearClase, subtituloDivider;
    EditText nombreInput, precioInput;
    Button btn_ingresar;
    DBhelper dBhelper;
    ClasesAdapter clasesAdapter;
    ListView lv_clases;
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
        lv_clases = findViewById(R.id.activity_clases__lv_clases);

        dBhelper = new DBhelper(Clases.this);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CabinSketch-Regular.ttf");
        titulo.setTypeface(typeface);
        crearClase.setTypeface(typeface);
        subtituloDivider.setTypeface(typeface);

        clasesAdapter = new ClasesAdapter(Clases.this, R.layout.alumno_row, dBhelper,dBhelper.buscarClases());
        lv_clases.setAdapter(clasesAdapter);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String nombre = nombreInput.getText().toString();
                    double precio = Double.parseDouble(precioInput.getText().toString().trim());

                    if (!nombre.trim().isEmpty() && precio > 0) {
                        ModeloClase clase = new ModeloClase(1, nombre, precio);
                        long exito = dBhelper.insertarClase(clase);

                        if (exito == -1) {
                            Toast.makeText(Clases.this, "Hubo un error al insertar la clase", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Clases.this, "Se creo la clase con exito", Toast.LENGTH_SHORT).show();

                            clasesAdapter = new ClasesAdapter(Clases.this, R.layout.alumno_row, dBhelper,dBhelper.buscarClases());
                            lv_clases.setAdapter(clasesAdapter);

                            nombreInput.setText("");
                            precioInput.setText("");
                        }
                    }
                }catch (Exception e) {
                    Toast.makeText(Clases.this, "Por favor ingrese valores validos", Toast.LENGTH_SHORT).show();
                }



            }
        });

        lv_clases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ModeloClase clase = (ModeloClase) adapterView.getItemAtPosition(i);

                dialogUpdate(clase, dBhelper);
            }
        });
    }

    private void dialogUpdate(ModeloClase clase, DBhelper db) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this ,R.style.WhiteDialog);

        View dialogLayout = LayoutInflater.from(this).inflate(R.layout.dialog__form_clase_update, null);
        final EditText inputNombre = dialogLayout.findViewById(R.id.dialog_form_clase_update__input_nombre);
        final EditText inputPrecio = dialogLayout.findViewById(R.id.dialog_form_clase_update__input_precio);

        inputNombre.setText(clase.getNombre_clase());
        inputPrecio.setText(String.valueOf(clase.getPrecio()));

        builder.setView(dialogLayout);

        builder.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try {
                    String valorNombre = inputNombre.getText().toString().trim();
                    double valorPrecio = Double.parseDouble(inputPrecio.getText().toString().trim());

                    clase.setNombre_clase(valorNombre);
                    clase.setPrecio(valorPrecio);
                    db.updateClase(clase);

                    clasesAdapter = new ClasesAdapter(Clases.this, R.layout.alumno_row, dBhelper,dBhelper.buscarClases());
                    lv_clases.setAdapter(clasesAdapter);
                }catch (Exception e) {
                    Toast.makeText(Clases.this, "Por favor ingreses valores validos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }
}