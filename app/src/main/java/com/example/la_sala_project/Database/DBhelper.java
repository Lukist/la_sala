package com.example.la_sala_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.la_sala_project.modelos.ModeloAlumno;
import com.example.la_sala_project.modelos.ModeloClase;
import com.example.la_sala_project.modelos.ModeloDeuda;
import com.example.la_sala_project.modelos.ModeloPaga;
import com.example.la_sala_project.modelos.ModeloPagoDeuda;
import com.example.la_sala_project.modelos.ModeloTutor;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "musicadb.db";
    private static final int DATABASE_VERSION = 1;

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        ///////////////////////////////////////////// CONSTRUCTOR /////////////////////////////////////////////////////////////
        SQLiteDatabase db = getWritableDatabase();
        if (!checkDatabaseExistence(db)) {
            // The database does not exist, copy it from the assets folder
            try {
                copyDatabase(context);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private boolean checkDatabaseExistence(SQLiteDatabase db) {
        String path = db.getPath();
        File file = new File(path);
        return file.exists();
    }

    private void copyDatabase(Context context) throws IOException {
        // Close the existing database
        close();

        // Open the empty database as the output stream
        OutputStream databaseOutputStream = new FileOutputStream(getDatabasePath(context));

        // Open the pre-populated database as the input stream
        InputStream databaseInputStream = context.getAssets().open("databases/" + DATABASE_NAME);

        // Transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = databaseInputStream.read(buffer)) > 0) {
            databaseOutputStream.write(buffer, 0, length);
        }

        // Close the streams
        databaseInputStream.close();
        databaseOutputStream.flush();
        databaseOutputStream.close();
    }

    private String getDatabasePath(Context context) {
        return context.getDatabasePath(DATABASE_NAME).getPath();
    }

    public List<Long> insertarAlumno(ModeloAlumno alumno, List<ModeloClase> clasesSeleccionadas) {
        SQLiteDatabase db = getWritableDatabase();
        List<Long> lista_ids = new ArrayList<>();
        long exito = 0;

        try {
            ContentValues cv = new ContentValues();

            cv.put("nombre", alumno.getNombre());
            cv.put("apellido", alumno.getApellido());

            long nuevaFila = db.insert("hijo_table", null, cv);
            if (nuevaFila != -1) {
                ContentValues contentValues = new ContentValues();

                for (ModeloClase clase : clasesSeleccionadas) {
                    contentValues.put("id_hijo", nuevaFila);
                    contentValues.put("id_clase", clase.getId_clase());

                    exito = db.insert("clases_hijo", null, contentValues);

                }
            }

            lista_ids.add(nuevaFila);
            lista_ids.add(exito);
            return lista_ids;
        } finally {
            db.close();
        }
    }

    public long insertarTutor(ModeloTutor tutor) {
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues cv = new ContentValues();

            cv.put("nombre", tutor.getNombre());
            cv.put("apellido", tutor.getApellido());
            cv.put("correo", tutor.getCorreo());
            cv.put("dni", tutor.getDni());
            cv.put("nro_telefono", tutor.getNro_telefono());
            cv.put("domicilio", tutor.getDomicilio());
            cv.put("id_hijo", tutor.getId_hijo());

            long nuevaFila = db.insert("tutor_table", null, cv);
            return nuevaFila;
        } finally {
            db.close();
        }
    }

    public List<ModeloAlumno> buscarAlumnos() {
        List<ModeloAlumno> returnList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT DISTINCT * FROM hijo_table";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int alumno_id = cursor.getInt(0);
                String alumno_nombre = cursor.getString(1);
                String alumno_apellido = cursor.getString(2);

                ModeloAlumno alumno = new ModeloAlumno(alumno_id, alumno_nombre, alumno_apellido);
                returnList.add(alumno);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public long insertarClase(ModeloClase clase) {
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues cv = new ContentValues();

            cv.put("nombre", clase.getNombre_clase());
            cv.put("precio", clase.getPrecio());

            long nuevaFila = db.insert("clases", null, cv);
            return nuevaFila;
        } finally {
            db.close();
        }
    }

    public List<ModeloClase> buscarClases() {
        List<ModeloClase> returnList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT DISTINCT * FROM clases";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int clase_id = cursor.getInt(0);
                String clase_nombre = cursor.getString(1);
                double clase_precio = cursor.getDouble(2);

                ModeloClase clase = new ModeloClase(clase_id, clase_nombre, clase_precio);

                returnList.add(clase);
            } while (cursor.moveToNext());

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public long insertarDeudda(ModeloDeuda deuda) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("id_tutor", deuda.getId_tutor());
        cv.put("id_hijo", deuda.getId_hijo());
        cv.put("id_clase", deuda.getId_clase());
        cv.put("fecha_deuda", deuda.getFecha_deuda());
        cv.put("hora_deuda", deuda.getHora_deuda());
        cv.put("monto_debido", deuda.getMonto_debido());
        cv.put("monto_debido_pagado", deuda.getMonto_debido_pagado());
        cv.put("deuda_cumplida_sn", deuda.getDeuda_cumplida_sn() ? 1 : 0);

        return db.insert("deudores_table", null, cv);
    }

    public List<ModeloDeuda> buscarDeudas() {
        List<ModeloDeuda> returnList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT DISTINCT * FROM deudores_table WHERE deuda_cumplida_sn = 0";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                long id_deuda = cursor.getLong(0);
                long id_tutor = cursor.getLong(1);
                long id_hijo = cursor.getLong(2);
                long id_clase = cursor.getLong(3);
                String fecha_deuda = cursor.getString(4);
                String hora_deuda = cursor.getString(5);
                double monto_debido = cursor.getDouble(6);
                double monto_debido_pagado = cursor.getDouble(7);
                int deuda_cumplida_sn = cursor.getInt(8);

                ModeloDeuda deuda = new ModeloDeuda(id_deuda, id_tutor, id_hijo, id_clase,fecha_deuda, hora_deuda, monto_debido, monto_debido_pagado, deuda_cumplida_sn == 1);
                returnList.add(deuda);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }

    public long insertarPago(ModeloPaga paga) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("id_tutor", paga.getId_tutor());
        cv.put("id_hijo", paga.getId_hijo());
        cv.put("id_clase", paga.getId_clase());
        cv.put("id_deuda", paga.getId_deuda());
        cv.put("fecha_pago", paga.getFecha_pago());
        cv.put("hora_pago", paga.getHora_pago());
        cv.put("monto_pagado", paga.getMonto_pagado());

        return db.insert("recibo_table", null, cv);
    }

    public long insertarPagoDeuda(ModeloPagoDeuda pagoDeuda) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("id_pago", pagoDeuda.getId_pago());
        cv.put("id_deuda", pagoDeuda.getId_deuda());
        cv.put("monto", pagoDeuda.getMonto());

        return db.insert("pago_deuda", null, cv);
    }

    public void deleteAlumno(long idAlumno) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete("hijo_table", "id_hijo = ?", new String[]{String.valueOf(idAlumno)});
        db.close();
    }

    public void deleteClase(long idClase) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete("clases", "id_clase = ?", new String[]{String.valueOf(idClase)});
        db.close();
    }

    public ModeloAlumno buscarAlumno(long idAlumno) {
        SQLiteDatabase db = getReadableDatabase();
        ModeloAlumno alumno = new ModeloAlumno();
        alumno.setIdAlumno(-1);

        String query = "SELECT * FROM hijo_table WHERE id_hijo = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idAlumno)});

        if (cursor != null && cursor.moveToFirst()) {
            alumno.setIdAlumno(cursor.getLong(0));
            alumno.setNombre(cursor.getString(1));
            alumno.setApellido(cursor.getString(2));
        }

        cursor.close();
        db.close();
        return alumno;
    }

    public ModeloTutor buscarTutorNombre(long alumnoId) {
        SQLiteDatabase db = getReadableDatabase();

        ModeloTutor tutor = new ModeloTutor();
        tutor.setNombre("tutor sin asignar");

        String query = "SELECT DISTINCT id_tutor, nombre FROM tutor_table WHERE id_hijo = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(alumnoId)});

        if (cursor != null && cursor.moveToFirst()) {
            tutor.setId_tutor(cursor.getLong(0));
            tutor.setNombre(cursor.getString(1));
        }

        return tutor;
    }

    public long insertarRecibo(ModeloPaga pago) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("id_tutor", pago.getId_tutor());
        cv.put("id_hijo", pago.getId_hijo());
        cv.put("id_clase", pago.getId_clase());
        cv.put("id_deuda", pago.getId_deuda());
        cv.put("fecha_pago", pago.getFecha_pago());
        cv.put("hora_pago", pago.getHora_pago());
        cv.put("monto_pagado", pago.getMonto_pagado());

        long exito = db.insert("recibo_table", null, cv);

        db.close();

        return exito;

    }

    public void updateClase(ModeloClase clase) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("nombre", clase.getNombre_clase());
        cv.put("precio", clase.getPrecio());

        String whereClause = "id_clase = ?";
        String[] whereArgs = { String.valueOf(clase.getId_clase()) };

        db.update("clases", cv, whereClause, whereArgs);

        db.close();
    }

    public void updateDeuda(ModeloDeuda deuda) {
        SQLiteDatabase db =  getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("monto_debido_pagado", deuda.getMonto_debido_pagado());
        cv.put("deuda_cumplida_sn", deuda.getDeuda_cumplida_sn());

        String whereClause = "id_deuda = ?";
        String[] whereArgs = { String.valueOf(deuda.getId_deuda()) };

        db.update("deudores_table", cv, whereClause, whereArgs);


    }

    public List<Long> buscarClase_hijo(long id_hijo) {
        SQLiteDatabase db = getReadableDatabase();
        List<Long> returnList = new ArrayList<>();

        String query = "SELECT * FROM clases_hijo WHERE id_hijo = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id_hijo)});

        if (cursor.moveToFirst()) {
            do {
                long id_clase = cursor.getLong(2);

                returnList.add(id_clase);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }


}
