package com.example.guillermo.proyecto224_clientesobras;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<ItemCliente> list=new ArrayList<>();
    private ItemAdapterCliente adapter;
    private RecyclerView recyclerView;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fetchRecords();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View mView1 = inflater.inflate(R.layout.activity_add_cliente,null);

                final EditText input_nombre=(EditText)mView1.findViewById(R.id.editNombre);
                final EditText input_direccion=(EditText)mView1.findViewById(R.id.editDireccion);
                final EditText input_cel=(EditText)mView1.findViewById(R.id.editCel);
                final EditText input_mail=(EditText)mView1.findViewById(R.id.editMail);
                final EditText input_descripcion=(EditText)mView1.findViewById(R.id.editDescripcion);
                final EditText input_monto=(EditText)mView1.findViewById(R.id.editMonto);
                final EditText input_finalizada=(EditText)mView1.findViewById(R.id.editFinalizada);
                final Button btnSuave=(Button) mView1.findViewById(R.id.btnSuave);



                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(mView1).setTitle("Añadir Cliente").setNegativeButton("Cancelar",
                        (dialogInterface,i) -> {
                            dialog.dismiss();
                        });

                btnSuave.setOnClickListener((view1) -> {
                    String nombre=input_nombre.getText().toString();
                    String direccion=input_direccion.getText().toString();
                    String cel=input_cel.getText().toString();
                    String mail=input_mail.getText().toString();
                    String descripcion=input_descripcion.getText().toString();
                    String monto=input_monto.getText().toString();
                    String finalizada=input_finalizada.getText().toString();

                    if(nombre.equals("") && direccion.equals("") && cel.equals("") && mail.equals("") &&
                            descripcion.equals("") && monto.equals("") && finalizada.equals("")){
                        Snackbar.make(view1,"Campos incompletos",Snackbar.LENGTH_SHORT).show();
                    }else{
                        Suave(nombre,direccion,cel,mail,descripcion,monto,finalizada);
                        dialog.dismiss();
                        Snackbar.make(view1,"Registrado",Snackbar.LENGTH_SHORT).show();
                    }
                });

                dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void fetchRecords() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerid);
        adapter = new ItemAdapterCliente(this,list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        list.clear();

        BDCliente functions = new BDCliente(MainActivity.this);

        ArrayList<ItemCliente> data = functions.getAllRecords();

        if (data.size()>0){
            for (int i = 0; i<data.size(); i++){

                int id = data.get(i).getId();
                String cnombre = data.get(i).getNombre();
                String cdireccion = data.get(i).getDireccion();
                String ccel = data.get(i).getCel();
                String cmail = data.get(i).getMail();
                String cdescripcion = data.get(i).getDescripcion_obra();
                String cmonto = data.get(i).getMonto();
                String cfinalizada = data.get(i).getFinalizada();

                ItemCliente itemCliente = new ItemCliente();

                itemCliente.setId(id);
                itemCliente.setNombre(cnombre);
                itemCliente.setDireccion(cdireccion);
                itemCliente.setCel(ccel);
                itemCliente.setMail(cmail);
                itemCliente.setDescripcion_obra(cdescripcion);
                itemCliente.setMonto(cmonto);
                itemCliente.setFinalizada(cfinalizada);

                list.add(itemCliente);

            }adapter.notifyDataSetChanged();

        }else {
            Toast.makeText(MainActivity.this, "No se encontro Registros", Toast.LENGTH_SHORT).show();
        }
    }

    public void Suave(String nombre,String direccion,String cel,String mail,String descripcion,
                      String monto,String finalizada){
        BDCliente functions = new BDCliente(MainActivity.this);
        ItemCliente itemCliente = new ItemCliente();

        itemCliente.setNombre(nombre);
        itemCliente.setDireccion(direccion);
        itemCliente.setCel(cel);
        itemCliente.setMail(mail);
        itemCliente.setDescripcion_obra(descripcion);
        itemCliente.setMonto(monto);
        itemCliente.setFinalizada(finalizada);
        functions.insertCliente(itemCliente);
        Toast.makeText(MainActivity.this,"Registrado..",Toast.LENGTH_SHORT).show();
        fetchRecords();
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
