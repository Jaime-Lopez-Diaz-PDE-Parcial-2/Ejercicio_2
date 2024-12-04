package com.example.ejercicio_2.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "actividades")
public class Actividad {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String fecha; // Cambiado a String
    private String hora;
    private String asignatura;

    public Actividad(int id, String fecha, String hora, String asignatura) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.asignatura = asignatura;
    }

    public Actividad() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }
}

