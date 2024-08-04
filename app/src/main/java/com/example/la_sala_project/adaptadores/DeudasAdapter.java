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
import com.example.la_sala_project.modelos.ModeloAlumno;
import com.example.la_sala_project.modelos.ModeloDeuda;
import com.example.la_sala_project.modelos.ModeloDeudaConNombre;

import org.w3c.dom.Text;

import java.util.List;

public class DeudasAdapter extends ArrayAdapter<ModeloDeudaConNombre> {

    private List<ModeloDeudaConNombre> lista_deudas;
    private Context mContext;
    private int resourceLayout;

    private DBhelper dBhelper;


    public DeudasAdapter(@NonNull Context context, int resource, DBhelper db, @NonNull List<ModeloDeudaConNombre> objects) {
        super(context, resource, objects);
        lista_deudas = objects;
        mContext = context;
        resourceLayout = resource;
        dBhelper = db;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null)
            view = LayoutInflater.from(mContext).inflate(resourceLayout, null);

        ModeloDeudaConNombre deuda = lista_deudas.get(position);

        TextView alumnoDeudor = view.findViewById(R.id.alumno_row__tv_nombre);
        alumnoDeudor.setText(deuda.getAlumno_nombre() + " " + deuda.getAlumno_apellido());

        TextView cantidad_deuda = view.findViewById(R.id.alumno_row__tv_apellido);
        cantidad_deuda.setText("AR$ " + String.valueOf(deuda.getDeuda().getMonto_debido()) + "\n" + deuda.getDeuda().getFecha_deuda() + "\n" + deuda.getClase_nombre());


        return view;
    }
}
