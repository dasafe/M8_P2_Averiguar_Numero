package com.example.averiguarnumero;

import android.view.View;

public class Jugador {
    private String nombre;
    private String ruta;

    public Jugador() {
    }

    public Jugador(String nombre, String ruta) {
        this.nombre = nombre;
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
