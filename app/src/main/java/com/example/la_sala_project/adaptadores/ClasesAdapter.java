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
import com.example.la_sala_project.modelos.ModeloClase;

import java.util.List;

public class ClasesAdapter extends ArrayAdapter<ModeloClase> {

    private List<ModeloClase> lista_clases;
    private Context mContext;
    private int resourceLayout;


    public ClasesAdapter(@NonNull Context context, int resource, @NonNull List<ModeloClase> objects) {
        super(context, resource, objects);

        lista_clases = objects;
        mContext = context;
        resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null)
            view = LayoutInflater.from(mContext).inflate(resourceLayout, null);

        ModeloClase clase = lista_clases.get(position);

        TextView titulo = view.findViewById(R.id.alumno_row__tv_nombre);
        titulo.setText(clase.getNombre_clase());

        TextView precio =  view.findViewById(R.id.alumno_row__tv_apellido);
        precio.setText(String.valueOf(clase.getPrecio()));

        return view;
    }
}
