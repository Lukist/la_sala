package com.example.la_sala_project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.la_sala_project.modelos.ModeloAlumno;
import com.example.la_sala_project.modelos.ModeloClase;
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
}
