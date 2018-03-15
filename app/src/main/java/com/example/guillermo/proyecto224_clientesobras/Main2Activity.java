package com.example.guillermo.proyecto224_clientesobras;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private ArrayList<ItemEmpleado> list=new ArrayList<>();
    private ItemAdapterEmpleado adapter;
    private RecyclerView recyclerView;
    private AlertDialog dialog;

    //String clienteid = getIntent().getStringExtra("id_cliente");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fetchRecordsEm();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View mView1 = inflater.inflate(R.layout.activity_add_empleado,null);

                final EditText input_nombre=(EditText)mView1.findViewById(R.id.editNombreEmpleado);
                final EditText input_actividad=(EditText)mView1.findViewById(R.id.editActividad);
                final EditText input_fechaini=(EditText)mView1.findViewById(R.id.editFechaInicio);
                final EditText input_fechafin=(EditText)mView1.findViewById(R.id.editFechaFin);
                final EditText input_cel=(EditText)mView1.findViewById(R.id.editCelu);
                final EditText input_cantidadobra=(EditText)mView1.findViewById(R.id.editCantidadObra);
                final EditText input_pagoestimado=(EditText)mView1.findViewById(R.id.editPagoEstimado);
                final Button btnSuave=(Button) mView1.findViewById(R.id.btnSuave2);

                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setView(mView1).setTitle("Añadir Empleado").setNegativeButton("Cancelar",
                        (dialogInterface,i) -> {
                            dialog.dismiss();
                        });

                btnSuave.setOnClickListener((view1) -> {
                    String nombre=input_nombre.getText().toString();
                    String actividad=input_actividad.getText().toString();
                    String fechaini=input_fechaini.getText().toString();
                    String fechafin=input_fechafin.getText().toString();
                    String cel=input_cel.getText().toString();
                    String cantidadobra=input_cantidadobra.getText().toString();
                    String pagoestimado=input_pagoestimado.getText().toString();

                    if(nombre.equals("") && actividad.equals("") && fechaini.equals("") && fechafin.equals("") &&
                            cel.equals("") && cantidadobra.equals("") && pagoestimado.equals("")){
                        Snackbar.make(view1,"Campos incompletos",Snackbar.LENGTH_SHORT).show();
                    }else{
                        Suave2(nombre,actividad,fechaini,fechafin,cel,cantidadobra,pagoestimado);
                        dialog.dismiss();
                        Snackbar.make(view1,"Registrado",Snackbar.LENGTH_SHORT).show();
                    }
                });

                dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void fetchRecordsEm() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerid2);
        adapter = new ItemAdapterEmpleado(this,list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(Main2Activity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        list.clear();

        BDCliente functions = new BDCliente(Main2Activity.this);
        String clienteid = getIntent().getStringExtra("id_cliente");

        ArrayList<ItemEmpleado> data = functions.getAllRecordsEm(clienteid+"");

        if (data.size()>0){
            for (int i = 0; i<data.size(); i++){

                int id = data.get(i).getId();
                String enombre = data.get(i).getNombre();
                String eactividad = data.get(i).getActividad();
                String efechaini = data.get(i).getFecha_inicio();
                String efechafin = data.get(i).getFecha_fin();
                String ecel = data.get(i).getCel();
                String ecantidadobra = data.get(i).getCantidad_obra();
                String epagoestimado = data.get(i).getPago_estimado();

                ItemEmpleado itemEmpleado = new ItemEmpleado();

                itemEmpleado.setId(id);
                itemEmpleado.setNombre(enombre);
                itemEmpleado.setActividad(eactividad);
                itemEmpleado.setFecha_inicio(efechaini);
                itemEmpleado.setFecha_fin(efechafin);
                itemEmpleado.setCel(ecel);
                itemEmpleado.setCantidad_obra(ecantidadobra);
                itemEmpleado.setPago_estimado(epagoestimado);

                list.add(itemEmpleado);

            }adapter.notifyDataSetChanged();

        }else {
            Toast.makeText(Main2Activity.this, "No se encontro Registros", Toast.LENGTH_SHORT).show();
        }
    }

    public void Suave2(String nombre,String actividad,String fechaini,String fechafin,String cel,
                      String cantidadobra,String pagoestimado){
        BDCliente functions = new BDCliente(Main2Activity.this);
        String clienteid = getIntent().getStringExtra("id_cliente");
        ItemEmpleado itemEmpleado = new ItemEmpleado();

        itemEmpleado.setNombre(nombre);
        itemEmpleado.setActividad(actividad);
        itemEmpleado.setFecha_inicio(fechaini);
        itemEmpleado.setFecha_fin(fechafin);
        itemEmpleado.setCel(cel);
        itemEmpleado.setCantidad_obra(cantidadobra);
        itemEmpleado.setPago_estimado(pagoestimado);
        functions.insertEmpleado(itemEmpleado);
        //functions.obtenerId(nombre);
        //Log.d("empleadore",clienteid+","+functions.obtenerId(nombre));
        functions.insertClienteEmpleado(clienteid,functions.obtenerId(nombre)+"");
        Toast.makeText(Main2Activity.this,"Registrado..",Toast.LENGTH_SHORT).show();
        fetchRecordsEm();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
