package com.example.ejercicio_2.ui.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio_2.R;
import com.example.ejercicio_2.model.Actividad;
import com.example.ejercicio_2.repos.room.RoomDatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;

public class CurrentTasksActivity extends AppCompatActivity {

    private TextView tvCurrentTasks;
    private RoomDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_tasks);

        databaseHelper = new RoomDatabaseHelper(this);
        tvCurrentTasks = findViewById(R.id.tvCurrentTask);

        loadCurrentTasks();
    }

    private void loadCurrentTasks() {
        Executors.newSingleThreadExecutor().execute(() -> {
            // Obtener la fecha actual en formato "YYYY-MM-DD"
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String currentDate = sdf.format(calendar.getTime());

            // Consultar la base de datos para obtener las tareas del día actual
            List<Actividad> tasksForToday = databaseHelper.getTasksByDay(currentDate);

            // Actualizar la UI en el hilo principal
            runOnUiThread(() -> {
                if (tasksForToday != null && !tasksForToday.isEmpty()) {
                    StringBuilder tasksText = new StringBuilder("Tareas para hoy:\n");
                    for (Actividad actividad : tasksForToday) {
                        tasksText.append("- ")
                                .append(actividad.getAsignatura())
                                .append(" a las ")
                                .append(actividad.getHora())
                                .append("\n");
                    }
                    tvCurrentTasks.setText(tasksText.toString());
                } else {
                    tvCurrentTasks.setText("No hay tareas para hoy.");
                }
            });
        });
    }
}
