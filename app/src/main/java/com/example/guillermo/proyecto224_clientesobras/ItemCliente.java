package com.example.guillermo.proyecto224_clientesobras;

/**
 * Created by guillermo on 12/03/18.
 */

public class ItemCliente {
    private int id;
    private String nombre;
    private String direccion;
    private String cel;
    private String mail;
    private String descripcion_obra;
    private String monto;
    private String finalizada;

    public ItemCliente(int id, String nombre, String direccion, String cel, String mail, String descripcion_obra, String monto, String finalizada) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cel = cel;
        this.mail = mail;
        this.descripcion_obra = descripcion_obra;
        this.monto = monto;
        this.finalizada = finalizada;
    }

    public ItemCliente() {
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDescripcion_obra() {
        return descripcion_obra;
    }

    public void setDescripcion_obra(String descripcion_obra) {
        this.descripcion_obra = descripcion_obra;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(String finalizada) {
        this.finalizada = finalizada;
    }
}
