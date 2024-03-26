package com.example.la_sala_project.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.la_sala_project.R;

public class MyDialog {

    public interface AlumnoIngreso {
        void registroAlumno(String textoNombre, String textoApellido);

    }

    public interface TutorIngreso {
        void registroTutor(String nombre, String apellido, String correo, String dni, String nro_telefono, String domicilio);
    }


    public static void showCustomInputDialog(Context context, String title, int layoutResId, final AlumnoIngreso listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.WhiteDialog);

        // Inflate the custom dialog layout
        View dialogLayout = LayoutInflater.from(context).inflate(layoutResId, null);
        final EditText inputNombre = dialogLayout.findViewById(R.id.dialog_form_tutor__input_nombre);
        final EditText inputApellido = dialogLayout.findViewById(R.id.dialog_form_alumno__input_apellido);
        builder.setView(dialogLayout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String textoNombre = inputNombre.getText().toString();
                String textoApellido = inputApellido.getText().toString();
                if (listener != null) {
                    listener.registroAlumno(textoNombre, textoApellido);


                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public static void showAnotherDialog(Context context, final TutorIngreso listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.WhiteDialog);

        View dialogLayout = LayoutInflater.from(context).inflate(R.layout.dialog__from_tutor, null);
        final EditText inputNombre = dialogLayout.findViewById(R.id.dialog_form_tutor__input_nombre);
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
