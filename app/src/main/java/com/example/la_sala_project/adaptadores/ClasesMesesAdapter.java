package com.example.la_sala_project.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.la_sala_project.R;
import com.example.la_sala_project.interfaces.PagoUpdateListener;
import com.example.la_sala_project.modelos.ModeloClase;

import org.w3c.dom.Text;

import java.util.List;

public class ClasesMesesAdapter extends ArrayAdapter<ModeloClase> {

    private List<ModeloClase> lista_clases;
    private Context mContext;
    private int resourceLayout;

    private String[] meses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

    private PagoUpdateListener listener;

    public ClasesMesesAdapter(@NonNull Context context, int resource, @NonNull List<ModeloClase> objects, PagoUpdateListener listener) {
        super(context, resource, objects);

        lista_clases = objects;
        mContext = context;
        resourceLayout = resource;
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null)
            view = LayoutInflater.from(mContext).inflate(resourceLayout, null);

        ModeloClase clase = lista_clases.get(position);

        TextView titulo_clase = view.findViewById(R.id.list_item_clase_mes__tv_titulo);
        titulo_clase.setText(clase.getNombre_clase());

        TextView cantidad_meses_pagar = view.findViewById(R.id.list_item_clase_mes__tv_meses);

        Spinner spinner = view.findViewById(R.id.list_item_clase_mes__spinner_meses);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, meses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String mesesSeleccionados = (String) parent.getItemAtPosition(position);

                listener.updatePago(Double.parseDouble(mesesSeleccionados));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }


}
