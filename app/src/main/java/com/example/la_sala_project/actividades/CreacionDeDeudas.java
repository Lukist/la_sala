package com.example.la_sala_project.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.la_sala_project.Database.DBhelper;
import com.example.la_sala_project.R;
import com.example.la_sala_project.adaptadores.AlumnoSelectiveAdapter;
import com.example.la_sala_project.adaptadores.ClasesAdapter;
import com.example.la_sala_project.adaptadores.ClasesMesesAdapter;
import com.example.la_sala_project.adaptadores.ClasesSelectiveAdapter;
import com.example.la_sala_project.adaptadores.alumnosAdapter;
import com.example.la_sala_project.interfaces.PagoUpdateListener;
import com.example.la_sala_project.modelos.ModeloAlumno;
import com.example.la_sala_project.modelos.ModeloClase;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Clase encargada de la creacion de deudas
 * Esta clase contara con una seleccion por listas asi como varios otros elementos que se iran mostrando y desaparaciendo
 * al cumplir con las condiciones adecuadas
 * */
public class CreacionDeDeudas extends AppCompatActivity {

    //INICIALIACION DE VARIABLES
    private ListView lv_listaDeAlumnos, lv_listaDeClases, lv_listaDeMesesPagar;
    private Button btn_salir, btn_siguiente;
    private TextView tv_labelAlumno, tv_labelClases;
    private AlumnoSelectiveAdapter alumnosAdapter;
    private ClasesSelectiveAdapter clasesAdapter;
    private ClasesMesesAdapter clasesMesesAdapter;
    private DBhelper dBhelper;
    private int selectedAlumnoPosition = -1;
    private Set<Integer> selectedClasePositions = new HashSet<>();
    private Set<Integer> posicionesMesesCantidad = new HashSet<>();

    private ModeloAlumno alumno;
    private List<ModeloClase> clasesSeleccionadas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_de_deudas);

        //ASIGNAMOS LOS VALORES CORRESPONDIENTES
        lv_listaDeAlumnos = findViewById(R.id.activity_creacion_de_deudas__lv_listaAlumnos);
        lv_listaDeClases = findViewById(R.id.activity_creacion_de_deudas__lv_listaClases);
        lv_listaDeMesesPagar = findViewById(R.id.activity_creacion_de_deudas__lv_listaMesesAPagar);
        btn_siguiente = findViewById(R.id.activity_creacion_de_deudas__btn_continuar);
        btn_salir = findViewById(R.id.activity_creacion_de_deudas__btn_salir);
        tv_labelAlumno = findViewById(R.id.activity_creacion_de_deudas__tv_label_alumnos);
        tv_labelClases = findViewById(R.id.activity_creacion_de_deudas__tv_labelClases);

        dBhelper = new DBhelper(CreacionDeDeudas.this);

        alumno = new ModeloAlumno();

        alumnosAdapter = new AlumnoSelectiveAdapter(CreacionDeDeudas.this, R.layout.alumno_row, dBhelper.buscarAlumnos());
        clasesAdapter = new ClasesSelectiveAdapter(CreacionDeDeudas.this, R.layout.alumno_row, dBhelper.buscarClases());
        lv_listaDeAlumnos.setAdapter(alumnosAdapter);
        lv_listaDeClases.setAdapter(clasesAdapter);


        /**
         * Listener que escucha el click de cada uno de los items de la lista
         *
         * Para conocer mas como funciona dirigirse a las clases adaptadores correspondientes
         * estas son las encargadas de manejar el comportamiento individual de los items.
         * */
        lv_listaDeAlumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                alumnosAdapter.setSelectedPosition(position);
                alumno = alumnosAdapter.getSelectedAlumno();
            }
        });

        /**
         * Listener que escucha el click de cada uno de los items de la lista
         *
         * Para conocer mas como funciona dirigirse a las clases adaptadores correspondientes
         * estas son las encargadas de manejar el comportamiento individual de los items.
         * */
        lv_listaDeClases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                clasesAdapter.toggleSelection(position);
                clasesSeleccionadas = clasesAdapter.getSelectedClases();
            }
        });

        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lv_listaDeAlumnos.getVisibility() == View.VISIBLE) {
                    lv_listaDeAlumnos.setVisibility(View.GONE);
                    lv_listaDeClases.setVisibility(View.GONE);
                    tv_labelClases.setVisibility(View.GONE);

                    lv_listaDeMesesPagar.setVisibility(View.VISIBLE);

                    clasesMesesAdapter = new ClasesMesesAdapter(CreacionDeDeudas.this, R.layout.list_item_clase_mes, clasesSeleccionadas, new PagoUpdateListener() {
                        @Override
                        public void updatePago(double monto, int cantidadMeses, int position) {

                        }
                    });

                    lv_listaDeMesesPagar.setAdapter(clasesMesesAdapter);

                } else if (lv_listaDeMesesPagar.getVisibility() == View.VISIBLE) {
                    recorrerClasesMeses();

                    lv_listaDeMesesPagar.setVisibility(View.GONE);
                    
                }

            }
        });


    }

    private void recorrerClasesMeses() {
        Map<Integer, Integer> selectedMonthsMap = clasesMesesAdapter.getSelectedMonthsMap();

        // Iterate through the entries in the selectedMonthsMap
        for (Map.Entry<Integer, Integer> entry : selectedMonthsMap.entrySet()) {
            int position = entry.getKey();
            int selectedMonths = entry.getValue();

            // Retrieve the corresponding ModeloClase object using the position
            ModeloClase clase = clasesMesesAdapter.getItem(position);

            // Process the selected months as needed
            Log.d("deudas debugger", "Class: " + clase.getNombre_clase() + ", Selected Months: " + selectedMonths);
        }
    }

}
