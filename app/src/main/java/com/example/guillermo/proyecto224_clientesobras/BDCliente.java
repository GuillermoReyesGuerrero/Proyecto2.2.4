package com.example.guillermo.proyecto224_clientesobras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by guillermo on 13/03/18.
 */

public class BDCliente extends SQLiteOpenHelper {

    private static final String DB_NAME="registros.db";
    private static final int DB_VERSION=1;

    private Cliente cliente = new Cliente();
    private Empleado empleado = new Empleado();
    private ClienteEmpleado clienteEmpleado = new ClienteEmpleado();

    public BDCliente(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //execute table cliente
        sqLiteDatabase.execSQL(Cliente.CREATE_TABLE_CLIENTE);
        sqLiteDatabase.execSQL(Empleado.CREATE_TABLE_EMPLEADO);
        sqLiteDatabase.execSQL(ClienteEmpleado.CREATE_TABLE_CLIENTE_EMPLEADO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Cliente.TABLE_CLIENTE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Empleado.TABLE_EMPLEADO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ClienteEmpleado.TABLE_CLIENTEEMPLEADO);
    }

    public void insertCliente(ItemCliente itemCliente){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Cliente.NOMBRE,itemCliente.getNombre());
        contentValues.put(Cliente.DIRECCION,itemCliente.getDireccion());
        contentValues.put(Cliente.CEL,itemCliente.getCel());
        contentValues.put(Cliente.MAIL,itemCliente.getMail());
        contentValues.put(Cliente.DESCRIPCION_OBRA,itemCliente.getDescripcion_obra());
        contentValues.put(Cliente.MONTO,itemCliente.getMonto());
        contentValues.put(Cliente.FINALIZADA,itemCliente.getFinalizada());

        database.insert(Cliente.TABLE_CLIENTE,null,contentValues);
    }

    public void insertEmpleado(ItemEmpleado itemEmpleado){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Empleado.NOMBRE,itemEmpleado.getNombre());
        contentValues.put(Empleado.ACTIVIDAD,itemEmpleado.getActividad());
        contentValues.put(Empleado.FECHA_INICIO,itemEmpleado.getFecha_inicio());
        contentValues.put(Empleado.FECHA_FIN,itemEmpleado.getFecha_fin());
        contentValues.put(Empleado.CEL,itemEmpleado.getCel());
        contentValues.put(Empleado.CANTIDAD_OBRA,itemEmpleado.getCantidad_obra());
        contentValues.put(Empleado.PAGO_ESTIMADO,itemEmpleado.getPago_estimado());

        Log.d("empleado",""+database.insert(Empleado.TABLE_EMPLEADO,null,contentValues));

    }
    public void insertClienteEmpleado(String id_cliente,String id_empleado){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ClienteEmpleado.ID_CLIENTE,id_cliente);
        contentValues.put(ClienteEmpleado.ID_EMPLEADO,id_empleado);

        database.insert(ClienteEmpleado.TABLE_CLIENTEEMPLEADO,null,contentValues);


        Log.d("clientere",id_cliente);
        Log.d("empleadore",id_empleado);
    }

    public int obtenerId(String nombreem){
        SQLiteDatabase database = getReadableDatabase();
        String sql = " SELECT * FROM " + Empleado.TABLE_EMPLEADO + " WHERE " + Empleado.NOMBRE + " = " +"'"+nombreem+"'";
        Cursor cursor = database.rawQuery(sql,null);

        if(cursor != null)
            cursor.moveToNext();
        ItemEmpleado itemEmpleado = new ItemEmpleado();
        itemEmpleado.setId(Integer.parseInt(cursor.getString(0)));

        cursor.close();
        database.close();
        return itemEmpleado.getId();
    }

    public ArrayList<ItemCliente> getAllRecords(){
        ArrayList<ItemCliente> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM " + Cliente.TABLE_CLIENTE;
        Cursor cursor = database.rawQuery(sql,null);

            while(cursor.moveToNext()){
                ItemCliente itemCliente = new ItemCliente();
                itemCliente.setId(Integer.parseInt(cursor.getString(0)));
                itemCliente.setNombre(cursor.getString(1));
                itemCliente.setDireccion(cursor.getString(2));
                itemCliente.setCel(cursor.getString(3));
                itemCliente.setMail(cursor.getString(4));
                itemCliente.setDescripcion_obra(cursor.getString(5));
                itemCliente.setMonto(cursor.getString(6));
                itemCliente.setFinalizada(cursor.getString(7));

                list.add(itemCliente);
            }
        cursor.close();
        database.close();
        return list;
    }

    public ArrayList<ItemEmpleado> getAllRecordsEm(String idcliente){
        ArrayList<ItemEmpleado> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT em.id,em.nombre,em.actividad,em.fecha_inicio,em.fecha_fin,em.cel,em.cantidad_obra,em.pago_estimado FROM " + Empleado.TABLE_EMPLEADO + " em "+
                "INNER JOIN clienteempleado ce " + "ON em.id = ce.id_empleado " +
                "WHERE ce.id_cliente = "+idcliente;
        Cursor cursor = database.rawQuery(sql,null);

        while(cursor.moveToNext()){
            ItemEmpleado itemEmpleado = new ItemEmpleado();
            itemEmpleado.setId(Integer.parseInt(cursor.getString(0)));
            itemEmpleado.setNombre(cursor.getString(1));
            itemEmpleado.setActividad(cursor.getString(2));
            itemEmpleado.setFecha_inicio(cursor.getString(3));
            itemEmpleado.setFecha_fin(cursor.getString(4));
            itemEmpleado.setCel(cursor.getString(5));
            itemEmpleado.setCantidad_obra(cursor.getString(6));
            itemEmpleado.setPago_estimado(cursor.getString(7));

            list.add(itemEmpleado);
        }
        cursor.close();
        database.close();
        return list;
    }

    public ItemCliente getSingleItem(int id){
        SQLiteDatabase database = getReadableDatabase();
        String sql = " SELECT * FROM " + Cliente.TABLE_CLIENTE + " WHERE " + Cliente.ID + "=?";
        Cursor cursor = database.rawQuery(sql,new String[]{String.valueOf(id)});

        if(cursor != null)
            cursor.moveToNext();

        ItemCliente itemCliente = new ItemCliente();
        itemCliente.setId(Integer.parseInt(cursor.getString(0)));
        itemCliente.setNombre(cursor.getString(1));
        itemCliente.setDireccion(cursor.getString(2));
        itemCliente.setCel(cursor.getString(3));
        itemCliente.setMail(cursor.getString(4));
        itemCliente.setDescripcion_obra(cursor.getString(5));
        itemCliente.setMonto(cursor.getString(6));
        itemCliente.setFinalizada(cursor.getString(7));

        cursor.close();
        database.close();
        return itemCliente;
    }

    public ItemEmpleado getSingleItemEm(int id){
        SQLiteDatabase database = getReadableDatabase();
        String sql = " SELECT * FROM " + Empleado.TABLE_EMPLEADO + " WHERE " + Empleado.ID + "=?";
        Cursor cursor = database.rawQuery(sql,new String[]{String.valueOf(id)});

        if(cursor != null)
            cursor.moveToNext();

        ItemEmpleado itemEmpleado = new ItemEmpleado();
        itemEmpleado.setId(Integer.parseInt(cursor.getString(0)));
        itemEmpleado.setNombre(cursor.getString(1));
        itemEmpleado.setActividad(cursor.getString(2));
        itemEmpleado.setFecha_inicio(cursor.getString(3));
        itemEmpleado.setFecha_fin(cursor.getString(4));
        itemEmpleado.setCel(cursor.getString(5));
        itemEmpleado.setCantidad_obra(cursor.getString(6));
        itemEmpleado.setPago_estimado(cursor.getString(7));

        cursor.close();
        database.close();
        return itemEmpleado;
    }

    public void updateCliente(ItemCliente itemCliente){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Cliente.NOMBRE,itemCliente.getNombre());
        contentValues.put(Cliente.DIRECCION,itemCliente.getDireccion());
        contentValues.put(Cliente.CEL,itemCliente.getCel());
        contentValues.put(Cliente.MAIL,itemCliente.getMail());
        contentValues.put(Cliente.DESCRIPCION_OBRA,itemCliente.getDescripcion_obra());
        contentValues.put(Cliente.MONTO,itemCliente.getMonto());
        contentValues.put(Cliente.FINALIZADA,itemCliente.getFinalizada());

        database.update(Cliente.TABLE_CLIENTE,contentValues,Cliente.ID + "=?",new String[]{String.valueOf(itemCliente.getId())});

    }

    public void updateEmpleado(ItemEmpleado itemEmpleado){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Empleado.NOMBRE,itemEmpleado.getNombre());
        contentValues.put(Empleado.ACTIVIDAD,itemEmpleado.getActividad());
        contentValues.put(Empleado.FECHA_INICIO,itemEmpleado.getFecha_inicio());
        contentValues.put(Empleado.FECHA_FIN,itemEmpleado.getFecha_fin());
        contentValues.put(Empleado.CEL,itemEmpleado.getCel());
        contentValues.put(Empleado.CANTIDAD_OBRA,itemEmpleado.getCantidad_obra());
        contentValues.put(Empleado.PAGO_ESTIMADO,itemEmpleado.getPago_estimado());

        database.update(Empleado.TABLE_EMPLEADO,contentValues,Empleado.ID + "=?",new String[]{String.valueOf(itemEmpleado.getId())});
    }
}
