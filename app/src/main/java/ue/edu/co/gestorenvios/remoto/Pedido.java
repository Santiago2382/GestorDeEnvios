package ue.edu.co.gestorenvios.remoto;

import java.util.List;

public class Pedido {
    private int usuario_id;
    private int cantidad_paquetes;
    private double ancho;
    private double alto;
    private String direccion;
    private String ciudad;
    private String barrio;
    private String nombre_receptor;
    private String celular_receptor;

    // Aquí añadimos el nuevo método
    public String getDimensiones() {
        return getAncho() + "x" + getAlto();
    }

    // getter and setter
    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getCantidad_paquetes() {
        return cantidad_paquetes;
    }

    public void setCantidad_paquetes(int cantidad_paquetes) {
        this.cantidad_paquetes = cantidad_paquetes;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getNombre_receptor() {
        return nombre_receptor;
    }

    public void setNombre_receptor(String nombre_receptor) {
        this.nombre_receptor = nombre_receptor;
    }

    public String getCelular_receptor() {
        return celular_receptor;
    }

    public void setCelular_receptor(String celular_receptor) {
        this.celular_receptor = celular_receptor;
    }
}

