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
import com.example.la_sala_project.modelos.ModeloClase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClasesSelectiveAdapter extends ArrayAdapter<ModeloClase> {
    private Context context;
    private List<ModeloClase> clases;
    private Set<Integer> selectedPositions = new HashSet<>(); // To track the selected items for multiple selection


    public ClasesSelectiveAdapter(@NonNull Context context, int resource, @NonNull List<ModeloClase> objects) {
        super(context, resource, objects);
        this.context = context;
        this.clases = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.alumno_row, parent, false);
        }

        ModeloClase clase = clases.get(position);
        TextView nombre = convertView.findViewById(R.id.alumno_row__tv_nombre);
        TextView precio = convertView.findViewById(R.id.alumno_row__tv_apellido);

        nombre.setText(clase.getNombre_clase());
        precio.setText("AR$ " + String.valueOf(clase.getPrecio()));
        RelativeLayout divTag = convertView.findViewById(R.id.alumno_row__div_tag);
        if (selectedPositions.contains(position)) {
            divTag.setBackgroundResource(R.drawable.list_item_default_selected);
        } else {
            divTag.setBackgroundResource(R.drawable.list_item_default);
        }

        return convertView;
    }

    public void toggleSelection(int position) {
        if (selectedPositions.contains(position)) {
            selectedPositions.remove(position);
        } else {
            selectedPositions.add(position);
        }
        notifyDataSetChanged();
    }

    public List<ModeloClase> getSelectedClases() {
        List<ModeloClase> selectedClases = new ArrayList<>();
        for (int position : selectedPositions) {
            selectedClases.add(clases.get(position));
        }
        return selectedClases;
    }
}
