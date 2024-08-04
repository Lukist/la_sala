package com.example.la_sala_project.adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.la_sala_project.R;
import com.example.la_sala_project.modelos.ModeloAlumno;

import java.util.List;

public class AlumnoSelectiveAdapter extends ArrayAdapter<ModeloAlumno> {

    private Context context;
    private List<ModeloAlumno> alumnos;
    private int selectedPosition = -1;

    public AlumnoSelectiveAdapter(@NonNull Context context, int resource, @NonNull List<ModeloAlumno> objects) {
        super(context, resource, objects);
        this.context = context;
        this.alumnos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.alumno_row, parent, false);
        }

        ModeloAlumno alumno = alumnos.get(position);
        TextView nombre = convertView.findViewById(R.id.alumno_row__tv_nombre);
        TextView apellido = convertView.findViewById(R.id.alumno_row__tv_apellido);

        nombre.setText(alumno.getNombre());
        apellido.setText(alumno.getApellido());
        RelativeLayout divTag = convertView.findViewById(R.id.alumno_row__div_tag);

        if (position == selectedPosition) {
            divTag.setBackgroundResource(R.drawable.list_item_default_selected);
        } else {
            divTag.setBackgroundResource(R.drawable.list_item_default);
        }

        return convertView;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();

    }

    public ModeloAlumno getSelectedAlumno() {
        if (selectedPosition != -1) {
            return alumnos.get(selectedPosition);
        }
        return null;
    }
}
