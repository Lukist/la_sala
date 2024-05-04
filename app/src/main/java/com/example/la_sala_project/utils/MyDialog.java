package com.example.la_sala_project.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.la_sala_project.Database.DBhelper;
import com.example.la_sala_project.R;
import com.example.la_sala_project.adaptadores.ClasesBuilderAdapter;
import com.example.la_sala_project.adaptadores.ClasesMesesAdapter;
import com.example.la_sala_project.interfaces.PagoUpdateListener;
import com.example.la_sala_project.modelos.ModeloAlumno;
import com.example.la_sala_project.modelos.ModeloClase;
import com.example.la_sala_project.modelos.ModeloDeuda;
import com.example.la_sala_project.modelos.ModeloPaga;
import com.example.la_sala_project.modelos.ModeloTutor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/***
 *
 * Esta clase maneja varios dialog builder complejos que tratan con carga de datos a una lista y tambien con interacciones con la base
 * de datos al mismo tiempo
 *
 * @author Lucas Palacios
 *
 */

public class MyDialog {

    /***
     *
     * Declaramos la variable de la base de datos
     *
     */
    static DBhelper db;


    /***
     *
     * Declaramos las interfaces
     *
     */

    /***
     *
     * La interface que manejara los inputs con el nombre y el apellodo del alumno
     *
     */
    public interface AlumnoIngreso {
        void registroAlumno(String textoNombre, String textoApellido, List<ModeloClase> selectedClasses);

    }

    /***
     * La interface que maneja los datos del tutor
     */
    public interface TutorIngreso {
        void registroTutor(String nombre, String apellido, String correo, String dni, String nro_telefono, String domicilio);
    }

    /***
     *La interfaz que maneja la cantidad de dinero pagada al registrar la clase
     */
    public interface  PagoIngreso {
        void registroPago(long exito);
    }

    /***
     * Metodo que llama al primer dialog builder para el registro de nombre, apellidos, y clases elegidas para inscribirse
     *
     * @param context El contexto en cual se llama al metodo
     * @param layoutResId El layout del dialog del dialog que usaremos
     * @param listener  Instancia de la interfaz para enviar los datos del alumno
     */
    public static void showCustomInputDialog(Context context,  int layoutResId, final AlumnoIngreso listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.WhiteDialog);
        db = new DBhelper(context);

        // Inflate the custom dialog layout
        View dialogLayout = LayoutInflater.from(context).inflate(layoutResId, null);
        final EditText inputNombre = dialogLayout.findViewById(R.id.dialog_form_alumno__input_nombre);
        final EditText inputApellido = dialogLayout.findViewById(R.id.dialog_form_alumno__input_apellido);
        final ListView listViewClases = dialogLayout.findViewById(R.id.dialog_form_alumno__list_clases);

        // Populate ListView with classes from database table
        List<ModeloClase> clasesList = db.buscarClases();


        ClasesBuilderAdapter clasesBuilderAdapter = new ClasesBuilderAdapter(context, R.layout.list_item_clase, clasesList);

        listViewClases.setAdapter(clasesBuilderAdapter);

        builder.setView(dialogLayout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String textoNombre = inputNombre.getText().toString();
                String textoApellido = inputApellido.getText().toString();
                SparseBooleanArray checkedItems = listViewClases.getCheckedItemPositions();
                List<ModeloClase> selectedClasses = new ArrayList<>();
                for (int i = 0; i < checkedItems.size(); i++) {
                    int position = checkedItems.keyAt(i);
                    if (checkedItems.valueAt(i)) {
                        selectedClasses.add(clasesList.get(position));
                    }
                }
                if (listener != null) {
                    listener.registroAlumno(textoNombre, textoApellido, selectedClasses);
                }
            }
        });

        // Other builder setup...

        builder.show();
    }

    /***
     * Dialog builder encargado de manejar los datos del tutor
     * @param context contexto desde el cual se llama al metodo
     * @param listener instancia de la interfaz de los datos del tutor
     */

    public static void showAnotherDialog(Context context, final TutorIngreso listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.WhiteDialog);

        View dialogLayout = LayoutInflater.from(context).inflate(R.layout.dialog__from_tutor, null);
        final EditText inputNombre = dialogLayout.findViewById(R.id.dialog_form_alumno__input_nombre);
        final EditText inputApellido = dialogLayout.findViewById(R.id.dialog_form_tutor__input_apellido);
        final EditText inputCorreo = dialogLayout.findViewById(R.id.dialog_form_tutor__input_correo);
        final EditText inputDni = dialogLayout.findViewById(R.id.dialog_form_tutor__input_dni);
        final EditText inputTelfono = dialogLayout.findViewById(R.id.dialog_form_tutor__input_telefono);
        final EditText inputDomicilio = dialogLayout.findViewById(R.id.dialog_form_tutor__input_domicilio);
        builder.setView(dialogLayout);

        builder.setPositiveButton("OK", null); // Set positive button to null initially

        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String textoNombre = inputNombre.getText().toString();
                        String textoApellido = inputApellido.getText().toString();
                        String textoCorreo = inputCorreo.getText().toString();
                        String textoDni = inputDni.getText().toString();
                        String textoTelefono = inputTelfono.getText().toString();
                        String textoDomicilio = inputDomicilio.getText().toString();

                        if (TextUtils.isEmpty(textoNombre) || TextUtils.isEmpty(textoApellido) ||
                                TextUtils.isEmpty(textoCorreo) || TextUtils.isEmpty(textoDni) ||
                                TextUtils.isEmpty(textoTelefono) || TextUtils.isEmpty(textoDomicilio)) {
                            // Show a message to the user
                            Toast.makeText(context, "Must complete all fields", Toast.LENGTH_SHORT).show();
                        } else {
                            // Dismiss the dialog and pass the data to the listener
                            if (listener != null) {
                                listener.registroTutor(textoNombre, textoApellido, textoCorreo, textoDni, textoTelefono, textoDomicilio);
                            }
                            dialog.dismiss(); // Dismiss the dialog
                        }
                    }
                });
            }
        });

        dialog.show();
    }

    /***
     * Diallog buider encargado de registrar la deuda del alumno segun la cantidad de meses seleccionadas para las clases
     *
     * @param context
     * @param deudaAlumnoNuevo
     * @param clasesElegidas
     * @param listener
     */

    public static void pagoDialog(Context context, double deudaAlumnoNuevo, List<ModeloClase> clasesElegidas, long id_alumno, List<ModeloDeuda> listaDeudas, final PagoIngreso listener) {
        double[] carritoDeClases = new double[clasesElegidas.size()];
        int[] mesesPorClase = new int[clasesElegidas.size()];
        final double[] sumatoria = {0};

        for (int i = 0; i < clasesElegidas.size(); i++) {
            carritoDeClases[i] = clasesElegidas.get(i).getPrecio();
            mesesPorClase[i] = 1;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.WhiteDialog);

        View dialogLayout = LayoutInflater.from(context).inflate(R.layout.dialog__form_pago, null);
        EditText pagoEfectuado = dialogLayout.findViewById(R.id.dialog_form_pago__input_monto);
        ListView listaClasesMeses = dialogLayout.findViewById(R.id.dialog_form_pago__lv_clases);

        ClasesMesesAdapter clasesMesesAdapter = new ClasesMesesAdapter(context, R.layout.list_item_clase_mes, clasesElegidas, new PagoUpdateListener() {
            @Override
            public void updatePago(double monto, int mesesSeleccionados,int position) {

                // Update the TextView here
                carritoDeClases[position] = monto;
                mesesPorClase[position] = mesesSeleccionados;

                sumatoria[0] = 0;

                for (double num : carritoDeClases) {
                    sumatoria[0] += num;
                }

                pagoEfectuado.setText(String.valueOf(sumatoria[0]));
            }
        });

        listaClasesMeses.setAdapter(clasesMesesAdapter);


        builder.setView(dialogLayout);

        pagoEfectuado.setText(String.valueOf(deudaAlumnoNuevo));


        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                for (int j = 0; j < clasesElegidas.size(); j++) {
                    double precioDePago = carritoDeClases[j] / mesesPorClase[j];

                    int contador = 0;
                    for (int h = 0; h < mesesPorClase[j]; h++) {
                        ModeloClase clase = clasesElegidas.get(j);


                        if (h == 0) {
                            listaDeudas.get(j).setMonto_debido_pagado(precioDePago);
                            listaDeudas.get(j).setDeuda_cumplida_sn(true);

                            db.updateDeuda(listaDeudas.get(j));

                            ModeloPaga pago = new ModeloPaga(0, db.buscarTutorNombre(id_alumno).getId_tutor(), id_alumno, clase.getId_clase(), listaDeudas.get(j).getId_deuda() ,new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()), new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()), precioDePago);

                            long exito = db.insertarRecibo(pago);
                        } else {
                            Calendar calendar = Calendar.getInstance();

                            calendar.add(Calendar.MONTH, contador);
                            Date newDate = calendar.getTime();
                            String formattedNewDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(newDate);
                            ModeloDeuda deudaDePagoAdelantado = new ModeloDeuda(0, db.buscarTutorNombre(id_alumno).getId_tutor(), id_alumno, clase.getId_clase(),formattedNewDate, new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()), clase.getPrecio(), precioDePago, true);
                            long idDeudaAdelanto = db.insertarDeudda(deudaDePagoAdelantado);

                            if (idDeudaAdelanto != -1) {
                                ModeloPaga pagoAdelantaddo = new ModeloPaga(0, db.buscarTutorNombre(id_alumno).getId_tutor(), id_alumno, clase.getId_clase(), idDeudaAdelanto ,new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()) , new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()), precioDePago);
                                db.insertarPago(pagoAdelantaddo);
                            }
                        }

                        contador++;
                    }
                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();


    }


}
