package com.example.guillermo.proyecto224_clientesobras;

/**
 * Created by guillermo on 12/03/18.
 */

public class ItemEmpleado {
    private int id;
    private String nombre;
    private String actividad;
    private String fecha_inicio;
    private String fecha_fin;
    private String cel;
    private String cantidad_obra;
    private String pago_estimado;

    public ItemEmpleado(int id, String nombre, String actividad, String fecha_inicio, String fecha_fin, String cel, String cantidad_obra, String pago_estimado) {
        this.id = id;
        this.nombre = nombre;
        this.actividad = actividad;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.cel = cel;
        this.cantidad_obra = cantidad_obra;
        this.pago_estimado = pago_estimado;
    }

    public ItemEmpleado(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getCantidad_obra() {
        return cantidad_obra;
    }

    public void setCantidad_obra(String cantidad_obra) {
        this.cantidad_obra = cantidad_obra;
    }

    public String getPago_estimado() {
        return pago_estimado;
    }

    public void setPago_estimado(String pago_estimado) {
        this.pago_estimado = pago_estimado;
    }
}
