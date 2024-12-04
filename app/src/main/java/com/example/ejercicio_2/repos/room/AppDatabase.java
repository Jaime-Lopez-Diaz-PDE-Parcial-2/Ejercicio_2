package com.example.ejercicio_2.repos.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.ejercicio_2.model.Actividad;
import com.example.ejercicio_2.repos.room.dao.ActividadDao;

@Database(entities = {Actividad.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ActividadDao actividadDao();
}

