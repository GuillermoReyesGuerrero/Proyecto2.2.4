package com.example.guillermo.proyecto224_clientesobras;

/**
 * Created by guillermo on 14/03/18.
 */

public class ClienteEmpleado {
    public static final String TABLE_CLIENTEEMPLEADO = "clienteempleado";

    public static final String ID_CLIENTE = "id_cliente";
    public static final String ID_EMPLEADO = "id_empleado";

    public static final String CREATE_TABLE_CLIENTE_EMPLEADO = "CREATE TABLE " + TABLE_CLIENTEEMPLEADO + "("
            + ID_CLIENTE + " TEXT,"
            + ID_EMPLEADO + " TEXT" + ")";

}
