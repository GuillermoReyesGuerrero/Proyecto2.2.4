package com.example.guillermo.proyecto224_clientesobras;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by guillermo on 13/03/18.
 */

public class ItemAdapterCliente extends RecyclerView.Adapter<ItemAdapterCliente.MyViewHolder>{
    private Activity activity;
    public ArrayList<ItemCliente> list;
    private AlertDialog dialog;

    public ItemAdapterCliente(Activity activity, ArrayList<ItemCliente> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //referencia para el contexto
        Context contex;
        contex = parent.getContext();
        //referencia para el inflater
        LayoutInflater nuevo;
        nuevo = LayoutInflater.from(contex);

        View itemview = nuevo.inflate(R.layout.item_list_cliente,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ItemCliente item= list.get(position);
        holder.txtNombre.setText("Nombre: "+item.getNombre());
        holder.txtDireccion.setText("Direccion: "+item.getDireccion());
        holder.txtFinalizada.setText("Finalizada: "+item.getFinalizada());

        holder.btnedit.setOnClickListener((view) -> {
            final int id = item.getId();

            LayoutInflater layoutInflater = activity.getLayoutInflater();
            View view1 = layoutInflater.inflate(R.layout.activity_edit_cliente,null);

            final EditText input_nombre=(EditText)view1.findViewById(R.id.editNombre);
            final EditText input_direccion=(EditText)view1.findViewById(R.id.editDireccion);
            final EditText input_cel=(EditText)view1.findViewById(R.id.editCel);
            final EditText input_mail=(EditText)view1.findViewById(R.id.editMail);
            final EditText input_descripcion=(EditText)view1.findViewById(R.id.editDescripcion);
            final EditText input_monto=(EditText)view1.findViewById(R.id.editMonto);
            final EditText input_finalizada=(EditText)view1.findViewById(R.id.editFinalizada);
            final Button btnSuave=(Button) view1.findViewById(R.id.btnSuave);

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setView(view1).setTitle("Editar Cliente").setNegativeButton("Cancelar",
                    (dialogInterface,i) -> {
                        dialog.dismiss();
                    });

            final BDCliente functions = new BDCliente(activity);
            final ItemCliente itemCliente = functions.getSingleItem(id);
            input_nombre.setText(itemCliente.getNombre());
            input_direccion.setText(itemCliente.getDireccion());
            input_cel.setText(itemCliente.getCel());
            input_mail.setText(itemCliente.getMail());
            input_descripcion.setText(itemCliente.getDescripcion_obra());
            input_monto.setText(itemCliente.getMonto());
            input_finalizada.setText(itemCliente.getFinalizada());

            btnSuave.setOnClickListener((view2) -> {
                String nombre=input_nombre.getText().toString();
                String direccion=input_direccion.getText().toString();
                String cel=input_cel.getText().toString();
                String mail=input_mail.getText().toString();
                String descripcion=input_descripcion.getText().toString();
                String monto=input_monto.getText().toString();
                String finalizada=input_finalizada.getText().toString();

                itemCliente.setNombre(nombre);
                itemCliente.setDireccion(direccion);
                itemCliente.setCel(cel);
                itemCliente.setMail(mail);
                itemCliente.setDescripcion_obra(descripcion);
                itemCliente.setMonto(monto);
                itemCliente.setFinalizada(finalizada);

                functions.updateCliente(itemCliente);

                Toast.makeText(activity,nombre + " Actualizado ",Toast.LENGTH_SHORT).show();
                ((MainActivity)activity).fetchRecords();
                dialog.dismiss();
            });
            dialog=builder.create();
            dialog.show();
        });

        holder.btndetail.setOnClickListener((view3) -> {
            final int id = item.getId();

            LayoutInflater layoutInflater = activity.getLayoutInflater();
            View view1 = layoutInflater.inflate(R.layout.activity_detail_cliente,null);

            final TextView input_nombre=view1.findViewById(R.id.editNombre);
            final TextView input_direccion=view1.findViewById(R.id.editDireccion);
            final TextView input_cel=view1.findViewById(R.id.editCel);
            final TextView input_mail=view1.findViewById(R.id.editMail);
            final TextView input_descripcion=view1.findViewById(R.id.editDescripcion);
            final TextView input_monto=view1.findViewById(R.id.editMonto);
            final TextView input_finalizada=view1.findViewById(R.id.editFinalizada);

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setView(view1).setTitle("Detalles Cliente").setNegativeButton("Cerrar",
                    (dialogInterface,i) -> {
                        dialog.dismiss();
                    });

            final BDCliente functions = new BDCliente(activity);
            final ItemCliente itemCliente = functions.getSingleItem(id);
            input_nombre.setText(itemCliente.getNombre());
            input_direccion.setText(itemCliente.getDireccion());
            input_cel.setText(itemCliente.getCel());
            input_mail.setText(itemCliente.getMail());
            input_descripcion.setText(itemCliente.getDescripcion_obra());
            input_monto.setText(itemCliente.getMonto());
            input_finalizada.setText(itemCliente.getFinalizada());

            dialog=builder.create();
            dialog.show();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtNombre;
        TextView txtDireccion;
        TextView txtFinalizada;

        Button btnedit;
        Button btndetail;

        Context context;


        public MyViewHolder(View itemView){
            super(itemView);

            txtNombre=itemView.findViewById(R.id.txtNombre);
            txtDireccion=itemView.findViewById(R.id.txtDireccion);
            txtFinalizada=itemView.findViewById(R.id.txtFinalizada);
            itemView.setOnClickListener(this);
            context = itemView.getContext();

            btnedit=itemView.findViewById(R.id.btnedit);
            btndetail=itemView.findViewById(R.id.btndetail);
        }

        @Override
        public void onClick(View view) {
            Intent miIntent;
            miIntent=new Intent(context,Main2Activity.class);
            miIntent.putExtra("id_cliente",list.get(getAdapterPosition()).getId()+"");
            context.startActivity(miIntent);
        }
    }
}
