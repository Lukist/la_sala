package com.example.la_sala_project.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.la_sala_project.Database.DBhelper;
import com.example.la_sala_project.R;
import com.example.la_sala_project.modelos.ModeloPaga;
import com.example.la_sala_project.modelos.ModeloPagaConNombre;

import java.util.List;

public class PagosAdapter extends ArrayAdapter<ModeloPagaConNombre> {

    private List<ModeloPagaConNombre> lista_pagos;
    private Context mContext;
    private int resourceLayout;


    public PagosAdapter(@NonNull Context context, int resource, @NonNull List<ModeloPagaConNombre> objects) {
        super(context, resource, objects);

        lista_pagos = objects;
        mContext = context;
        resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null)
            view = LayoutInflater.from(mContext).inflate(resourceLayout, null);

        ModeloPagaConNombre pago = lista_pagos.get(position);

        TextView cantidadPago = view.findViewById(R.id.alumno_row__tv_nombre);
        cantidadPago.setText("AR$ " + String.valueOf(pago.getPaga().getMonto_pagado()));

        TextView nombreTutor = view.findViewById(R.id.alumno_row__tv_apellido);
        nombreTutor.setText(pago.getTutorNombre());



        return view;
    }
}
