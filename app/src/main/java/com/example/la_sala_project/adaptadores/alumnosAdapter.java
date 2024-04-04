package com.example.la_sala_project.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.la_sala_project.R;
import com.example.la_sala_project.modelos.ModeloAlumno;
import com.example.la_sala_project.modelos.ModeloTutor;

import java.util.List;

public class alumnosAdapter extends ArrayAdapter<ModeloAlumno> {

    private List<ModeloAlumno> lista_alumnos;
    private Context mContext;
    private int resourceLayout;

    public alumnosAdapter(@NonNull Context context, int resource, @NonNull List<ModeloAlumno> objects) {
        super(context, resource, objects);
        lista_alumnos = objects;
        mContext = context;
        resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null)
            view = LayoutInflater.from(mContext).inflate(resourceLayout, null);

        ModeloAlumno alumno = lista_alumnos.get(position);


        TextView alumno_nombre = view.findViewById(R.id.alumno_row__tv_nombre);
        alumno_nombre.setText(alumno.getNombre());

        TextView alumno_apellido = view.findViewById(R.id.alumno_row__tv_apellido);
        alumno_apellido.setText(alumno.getApellido());



        return view;
    }
}
