package com.example.guillermo.proyecto224_clientesobras;

import android.app.Activity;
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

public class ItemAdapterEmpleado extends RecyclerView.Adapter<ItemAdapterEmpleado.MyViewHolder>{

    private Activity activity;
    private ArrayList<ItemEmpleado> list;
    private AlertDialog dialog;

    public ItemAdapterEmpleado(Activity activity, ArrayList<ItemEmpleado> list) {
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

        View itemview = nuevo.inflate(R.layout.item_list_empleado,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ItemEmpleado item= list.get(position);
        holder.txtNombreEmpleado.setText("Nombre: "+item.getNombre());
        holder.txtActividad.setText("Actividad: "+item.getActividad());
        holder.txtCelu.setText("Cel: "+item.getCel());

        holder.btnedit.setOnClickListener((view) -> {
            final int id = item.getId();

            LayoutInflater layoutInflater = activity.getLayoutInflater();
            View view1 = layoutInflater.inflate(R.layout.activity_edit_empleado,null);

            final EditText input_nombre=(EditText)view1.findViewById(R.id.editNombreEmpleado);
            final EditText input_actividad=(EditText)view1.findViewById(R.id.editActividad);
            final EditText input_fecini=(EditText)view1.findViewById(R.id.editFechaInicio);
            final EditText input_fecfin=(EditText)view1.findViewById(R.id.editFechaFin);
            final EditText input_cel=(EditText)view1.findViewById(R.id.editCelu);
            final EditText input_cantidad=(EditText)view1.findViewById(R.id.editCantidadObra);
            final EditText input_pago=(EditText)view1.findViewById(R.id.editPagoEstimado);
            final Button btnSuave=(Button) view1.findViewById(R.id.btnSuave2);

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setView(view1).setTitle("Editar Empleado").setNegativeButton("Cancelar",
                    (dialogInterface,i) -> {
                        dialog.dismiss();
                    });

            final BDCliente functions = new BDCliente(activity);
            final ItemEmpleado itemEmpleado = functions.getSingleItemEm(id);
            input_nombre.setText(itemEmpleado.getNombre());
            input_actividad.setText(itemEmpleado.getActividad());
            input_fecini.setText(itemEmpleado.getFecha_inicio());
            input_fecfin.setText(itemEmpleado.getFecha_fin());
            input_cel.setText(itemEmpleado.getCel());
            input_cantidad.setText(itemEmpleado.getCantidad_obra());
            input_pago.setText(itemEmpleado.getPago_estimado());

            btnSuave.setOnClickListener((view2) -> {
                String nombre=input_nombre.getText().toString();
                String actividad=input_actividad.getText().toString();
                String fechaini=input_fecini.getText().toString();
                String fechafin=input_fecfin.getText().toString();
                String cel=input_cel.getText().toString();
                String cantidadobra=input_cantidad.getText().toString();
                String pagoestimado=input_pago.getText().toString();

                itemEmpleado.setNombre(nombre);
                itemEmpleado.setActividad(actividad);
                itemEmpleado.setFecha_inicio(fechaini);
                itemEmpleado.setFecha_fin(fechafin);
                itemEmpleado.setCel(cel);
                itemEmpleado.setCantidad_obra(cantidadobra);
                itemEmpleado.setPago_estimado(pagoestimado);

                functions.updateEmpleado(itemEmpleado);

                Toast.makeText(activity,nombre + " Actualizado ",Toast.LENGTH_SHORT).show();
                ((Main2Activity)activity).fetchRecordsEm();
                dialog.dismiss();
            });
            dialog=builder.create();
            dialog.show();
        });

        holder.btndetail.setOnClickListener((view3) -> {
            final int id = item.getId();

            LayoutInflater layoutInflater = activity.getLayoutInflater();
            View view1 = layoutInflater.inflate(R.layout.activity_detail_empleado,null);

            final TextView input_nombre=view1.findViewById(R.id.editNombreEmpleado);
            final TextView input_actividad=view1.findViewById(R.id.editActividadEm);
            final TextView input_fechaini=view1.findViewById(R.id.editFechaInicioEm);
            final TextView input_fechafin=view1.findViewById(R.id.editFechaFinEm);
            final TextView input_cel=view1.findViewById(R.id.editCeluEm);
            final TextView input_cantidadobra=view1.findViewById(R.id.editCantidadObraEm);
            final TextView input_pagoestimado=view1.findViewById(R.id.editPagoEstimadoEm);

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setView(view1).setTitle("Detalles Empleado").setNegativeButton("Cerrar",
                    (dialogInterface,i) -> {
                        dialog.dismiss();
                    });

            final BDCliente functions = new BDCliente(activity);
            final ItemEmpleado itemEmpleado = functions.getSingleItemEm(id);
            input_nombre.setText(itemEmpleado.getNombre());
            input_actividad.setText(itemEmpleado.getActividad());
            input_fechaini.setText(itemEmpleado.getFecha_inicio());
            input_fechafin.setText(itemEmpleado.getFecha_fin());
            input_cel.setText(itemEmpleado.getCel());
            input_cantidadobra.setText(itemEmpleado.getCantidad_obra());
            input_pagoestimado.setText(itemEmpleado.getPago_estimado());

            dialog=builder.create();
            dialog.show();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombreEmpleado;
        TextView txtActividad;
        TextView txtCelu;


        Button btnedit;
        Button btndetail;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtNombreEmpleado=itemView.findViewById(R.id.txtNombreEmpleado);
            txtActividad=itemView.findViewById(R.id.txtActividad);
            txtCelu=itemView.findViewById(R.id.txtCelu);

            btnedit=itemView.findViewById(R.id.btneditEm);
            btndetail=itemView.findViewById(R.id.btndetailEm);
        }
    }
}
