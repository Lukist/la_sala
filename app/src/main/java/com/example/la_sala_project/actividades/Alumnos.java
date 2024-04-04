package com.example.la_sala_project.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.la_sala_project.Database.DBhelper;
import com.example.la_sala_project.R;
import com.example.la_sala_project.adaptadores.alumnosAdapter;
import com.example.la_sala_project.modelos.ModeloAlumno;
import com.example.la_sala_project.modelos.ModeloTutor;
import com.example.la_sala_project.utils.MyDialog;

public class Alumnos extends AppCompatActivity {

    TextView titulo, subtitulo;
    ImageButton btn_agregar;
    ModeloAlumno alumno;
    ModeloTutor tutor;
    DBhelper dBhelper;
    private alumnosAdapter alumnosAdapter;
    private ListView lv_alumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        titulo = findViewById(R.id.activiy_alumnos__tv_titulo);
        subtitulo = findViewById(R.id.activiy_alumnos__tv_subtitulo);
        btn_agregar = findViewById(R.id.activiy_alumnos__ib_btn_agregar);
        lv_alumnos = findViewById(R.id.activiy_alumnos__lv_lista_alumnos);


        alumno = new ModeloAlumno();
        tutor = new ModeloTutor();

        dBhelper = new DBhelper(Alumnos.this);

        alumnosAdapter = new alumnosAdapter(Alumnos.this, R.layout.alumno_row, dBhelper.buscarAlumnos());
        lv_alumnos.setAdapter(alumnosAdapter);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CabinSketch-Regular.ttf");

        titulo.setTypeface(typeface);
        subtitulo.setTypeface(typeface);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog.showCustomInputDialog(Alumnos.this, "Ingresar", R.layout.dialog__form_alumno, new MyDialog.AlumnoIngreso() {
                    @Override
                    public void registroAlumno(String textoNombre, String textoApellido) {
                        alumno.setNombre(textoNombre);
                        alumno.setApellido(textoApellido);

                        long resultId = dBhelper.insertarAlumno(alumno);
                        if (resultId == -1) {
                            Toast.makeText(Alumnos.this, "Ha habido un error", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Alumnos.this, "Se ha registrado un alumno", Toast.LENGTH_SHORT).show();
                            alumnosAdapter = new alumnosAdapter(Alumnos.this, R.layout.alumno_row, dBhelper.buscarAlumnos());
                            lv_alumnos.setAdapter(alumnosAdapter);
                            MyDialog.showAnotherDialog(Alumnos.this, new MyDialog.TutorIngreso() {
                                @Override
                                public void registroTutor(String nombre, String apellido, String correo, String dni, String nro_telefono, String domicilio) {
                                    tutor.setNombre(nombre);
                                    tutor.setApellido(apellido);
                                    tutor.setCorreo(correo);
                                    tutor.setDni(dni);
                                    tutor.setTelefono(nro_telefono);
                                    tutor.setDomicilio(domicilio);
                                    tutor.setId_hijo(resultId);

                                    long resultadoTutorId = dBhelper.insertarTutor(tutor);

                                    if (resultadoTutorId == -1) {
                                        Toast.makeText(Alumnos.this, "Hubo un error al registrar al tutor", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Alumnos.this, "Registro del tutor exitoso", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}