package com.example.guillermo.proyecto224_clientesobras;

/**
 * Created by guillermo on 12/03/18.
 */

public class Empleado {
    public static final String TABLE_EMPLEADO = "empleado";

    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String ACTIVIDAD = "actividad";
    public static final String FECHA_INICIO = "fecha_inicio";
    public static final String FECHA_FIN = "fecha_fin";
    public static final String CEL = "cel";
    public static final String CANTIDAD_OBRA = "cantidad_obra";
    public static final String PAGO_ESTIMADO = "pago_estimado";

    public static final String CREATE_TABLE_EMPLEADO = "CREATE TABLE " + TABLE_EMPLEADO + "("
            + ID + " INTEGER PRIMARY KEY,"
            + NOMBRE + " TEXT,"
            + ACTIVIDAD + " TEXT,"
            + FECHA_INICIO + " TEXT,"
            + FECHA_FIN + " TEXT,"
            + CEL + " TEXT,"
            + CANTIDAD_OBRA + " TEXT,"
            + PAGO_ESTIMADO + " TEXT" + ")";
}
