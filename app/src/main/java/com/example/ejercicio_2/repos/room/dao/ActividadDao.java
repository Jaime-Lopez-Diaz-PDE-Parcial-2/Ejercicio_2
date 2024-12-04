package com.example.ejercicio_2.repos.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ejercicio_2.model.Actividad;

import java.util.List;

@Dao
public interface ActividadDao {

    @Insert
    void insert(Actividad actividad);

    // Método para obtener actividades por día
    @Query("SELECT * FROM actividades WHERE fecha = :fecha")
    List<Actividad> getByDay(String fecha);

    @Query("SELECT * FROM actividades WHERE hora = :hora LIMIT 1")
    Actividad getCurrentTask(String hora);

    @Query("SELECT * FROM actividades")
    List<Actividad> getAll();

}
