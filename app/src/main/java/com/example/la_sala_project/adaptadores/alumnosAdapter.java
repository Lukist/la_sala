package com.example.la_sala_project.adaptadores;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.la_sala_project.Database.DBhelper;
import com.example.la_sala_project.R;
import com.example.la_sala_project.modelos.ModeloAlumno;
import com.example.la_sala_project.modelos.ModeloTutor;

import java.util.List;

public class alumnosAdapter extends ArrayAdapter<ModeloAlumno> {

    private List<ModeloAlumno> lista_alumnos;
    private Context mContext;
    private int resourceLayout;

    private DBhelper db;

    public alumnosAdapter(@NonNull Context context, int resource, DBhelper dBhelper,@NonNull List<ModeloAlumno> objects) {
        super(context, resource, objects);
        lista_alumnos = objects;
        mContext = context;
        resourceLayout = resource;
        db = dBhelper;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null)
            view = LayoutInflater.from(mContext).inflate(resourceLayout, null);

        ModeloAlumno alumno = lista_alumnos.get(position);


        TextView alumno_nombre = view.findViewById(R.id.alumno_row__tv_nombre);
        alumno_nombre.setText(alumno.getNombre() + " " + alumno.getApellido());

        TextView tutorNombre = view.findViewById(R.id.alumno_row__tv_apellido);
        tutorNombre.setText(db.buscarTutorNombre(alumno.getIdAlumno()).getNombre());

        ImageButton deleteButton = view.findViewById(R.id.alumno_row__btn_borrar);

        // Set click listener for the delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show confirmation dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.WhiteDialog);
                builder.setMessage("¿Esta seguro de que desea eliminar a este alumno?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // User confirmed deletion
                                // Remove the item from the list
                                lista_alumnos.remove(position);
                                // Notify adapter that data set has changed
                                notifyDataSetChanged();
                                // Delete the alumno from the database
                                db.deleteAlumno(alumno.getIdAlumno());
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // User canceled deletion, do nothing
                            }
                        })
                        .show();
            }
        });

        return view;
    }
}
