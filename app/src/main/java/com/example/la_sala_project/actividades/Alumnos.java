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
import com.example.la_sala_project.modelos.ModeloClase;
import com.example.la_sala_project.modelos.ModeloDeuda;
import com.example.la_sala_project.modelos.ModeloPaga;
import com.example.la_sala_project.modelos.ModeloTutor;
import com.example.la_sala_project.utils.MyDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Palacios Lucas <lucaspalacios.lsp@gmail.com>
 *
 * Esta pantalla esta creada para manejar la inscripcion de alumnos al sistema, asi como para su edicion si esta es requerida.
 *
 *
 * */

public class Alumnos extends AppCompatActivity {

    /**
     * Inicializacion de variables
     * */
    TextView titulo, subtitulo;
    ImageButton btn_agregar;
    ModeloAlumno alumno;
    ModeloTutor tutor;
    DBhelper dBhelper;
    private alumnosAdapter alumnosAdapter;
    private ListView lv_alumnos;
    double precioSum;

    /**
     * Funcion principal de la pantalla
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);


        /**
         * Asignacion de id's
         * */
        titulo = findViewById(R.id.activiy_alumnos__tv_titulo);
        subtitulo = findViewById(R.id.activiy_alumnos__tv_subtitulo);
        btn_agregar = findViewById(R.id.activiy_alumnos__ib_btn_agregar);
        lv_alumnos = findViewById(R.id.activiy_alumnos__lv_lista_alumnos);

        /**
         * Cramos objetos representando las tablas en la base de datos
         * */
        alumno = new ModeloAlumno();
        tutor = new ModeloTutor();

        /**
         * Creamos una instancia de la base de datos
         * */
        dBhelper = new DBhelper(Alumnos.this);

        /**
         * Cargamos la lista de alumnos ingresados al sistema si es que hay
         * */
        alumnosAdapter = new alumnosAdapter(Alumnos.this, R.layout.alumno_row, dBhelper,dBhelper.buscarAlumnos());
        lv_alumnos.setAdapter(alumnosAdapter);

        /**
         * Setea la tipografia del titulo
         * */
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CabinSketch-Regular.ttf");
        titulo.setTypeface(typeface);
        subtitulo.setTypeface(typeface);

        /**
         * Manejamos lo que ocurrira cuando clickeamos el boton "agregar"
         * */
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Nos mostrara el dialog y el builder con los editText para completar con la info deel alumno
                 * */
                MyDialog.showCustomInputDialog(Alumnos.this, R.layout.dialog__form_alumno, new MyDialog.AlumnoIngreso() {
                    /**
                     * Interfaz utilizada para recuperar los datos dados por el usuario en el builder
                     * */
                    @Override
                    public void registroAlumno(String textoNombre, String textoApellido, List<ModeloClase> selectedClasses) {

                        List<ModeloDeuda> listaDeudasACrear = new ArrayList<>();

                        alumno.setNombre(textoNombre);
                        alumno.setApellido(textoApellido);


                        precioSum = 0;
                        for (ModeloClase clase : selectedClasses) {
                            double aux = clase.getPrecio();
                            precioSum += aux;
                        }



                        /**
                         * Checkeamos si no hubo un error al insertar los datos en la base de datos
                         * */

                        Toast.makeText(Alumnos.this, "Se ha registrado un alumno", Toast.LENGTH_SHORT).show();
                        alumnosAdapter = new alumnosAdapter(Alumnos.this, R.layout.alumno_row, dBhelper,dBhelper.buscarAlumnos());


                        MyDialog.showAnotherDialog(Alumnos.this, new MyDialog.TutorIngreso() {
                            @Override
                            public void registroTutor(String nombre, String apellido, String correo, String dni, String nro_telefono, String domicilio) {
                                tutor.setNombre(nombre);
                                tutor.setApellido(apellido);
                                tutor.setCorreo(correo);
                                tutor.setDni(dni);
                                tutor.setTelefono(nro_telefono);
                                tutor.setDomicilio(domicilio);


                                List<Long> resultId = dBhelper.insertarAlumno(alumno, selectedClasses);

                                if (resultId.get(0) == -1 || resultId.get(1) == -1) {
                                    Toast.makeText(Alumnos.this, "Ha habido un error al registrar el alumnos", Toast.LENGTH_SHORT).show();
                                } else {
                                    tutor.setId_hijo(resultId.get(0));
                                    long resultadoTutorId = dBhelper.insertarTutor(tutor);

                                    if (resultadoTutorId == -1) {
                                        Toast.makeText(Alumnos.this, "Hubo un error al registrar al tutor", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Alumnos.this, "Registro del tutor exitoso", Toast.LENGTH_SHORT).show();

                                        for (ModeloClase clase : selectedClasses) {
                                            ModeloDeuda deuda = new ModeloDeuda(0, resultadoTutorId, resultId.get(0), clase.getId_clase(),new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()), new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()), clase.getPrecio(), 0, false);

                                            long exitoDeuda = dBhelper.insertarDeudda(deuda);

                                            if (exitoDeuda != -1) {
                                                Toast.makeText(Alumnos.this, "deuda creada", Toast.LENGTH_SHORT).show();

                                                listaDeudasACrear.add(deuda);
                                            }

                                        }

                                        MyDialog.pagoDialog(Alumnos.this, precioSum, selectedClasses, resultId.get(0),listaDeudasACrear,new MyDialog.PagoIngreso() {
                                            @Override
                                            public void registroPago(long exito) {

                                            }
                                        });
                                    }
                                }

                            }


                        });

                    }
                });
            }
        });
    }
}