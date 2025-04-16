package com.example.mascotas;

import java.io.Serializable;

public class Mascota implements Serializable {

    int foto;
    String nombre;
    int numLikes;

    public Mascota(int foto, String nombre, int numLikes) {
        this.foto = foto;
        this.nombre = nombre;
        this.numLikes = numLikes;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }
}
