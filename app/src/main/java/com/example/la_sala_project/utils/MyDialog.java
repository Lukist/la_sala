package com.example.la_sala_project.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.la_sala_project.Database.DBhelper;
import com.example.la_sala_project.R;
import com.example.la_sala_project.adaptadores.ClasesBuilderAdapter;
import com.example.la_sala_project.modelos.ModeloClase;

import java.util.ArrayList;
import java.util.List;



public class MyDialog {

    static DBhelper db;


    public interface AlumnoIngreso {
        void registroAlumno(String textoNombre, String textoApellido, List<ModeloClase> selectedClasses);

    }

    public interface TutorIngreso {
        void registroTutor(String nombre, String apellido, String correo, String dni, String nro_telefono, String domicilio);
    }


    public static void showCustomInputDialog(Context context, String title, int layoutResId, final AlumnoIngreso listener) {
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

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String textoNombre = inputNombre.getText().toString();
                String textoApellido = inputApellido.getText().toString();
                String textoCorreo = inputCorreo.getText().toString();
                String textoDni = inputDni.getText().toString();
                String textoTelefono = inputTelfono.getText().toString();
                String textoDomicilio = inputDomicilio.getText().toString();

                if (listener != null) {
                    listener.registroTutor(textoNombre, textoApellido, textoCorreo, textoDni, textoTelefono, textoDomicilio);
                }
            }
        });

        builder.show();
    }
}
