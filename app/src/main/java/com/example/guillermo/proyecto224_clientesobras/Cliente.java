package com.example.guillermo.proyecto224_clientesobras;

/**
 * Created by guillermo on 12/03/18.
 */

public class Cliente {
    public static final String TABLE_CLIENTE = "cliente";

    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String DIRECCION = "direccion";
    public static final String CEL = "cel";
    public static final String MAIL = "mail";
    public static final String DESCRIPCION_OBRA = "descripcion_obra";
    public static final String MONTO = "monto";
    public static final String FINALIZADA = "finalizada";



    public static final String CREATE_TABLE_CLIENTE = "CREATE TABLE " + TABLE_CLIENTE + "("
            + ID + " INTEGER PRIMARY KEY,"
            + NOMBRE + " TEXT,"
            + DIRECCION + " TEXT,"
            + CEL + " TEXT,"
            + MAIL + " TEXT,"
            + DESCRIPCION_OBRA + " TEXT,"
            + MONTO + " TEXT,"
            + FINALIZADA + " TEXT" + ")";
}
