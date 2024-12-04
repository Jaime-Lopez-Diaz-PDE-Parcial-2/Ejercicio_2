package com.example.ejercicio_2.ui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio_2.R;
import com.example.ejercicio_2.model.Actividad;
import com.example.ejercicio_2.repos.room.RoomDatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executors;

public class CalendarActivity extends AppCompatActivity {

    private TextView tvCurrentMonth, tvEmptyMessage;
    private GridLayout gridCalendar;
    private RoomDatabaseHelper databaseHelper;

    private Calendar calendar = Calendar.getInstance();
    private Map<Integer, Actividad> dayActivityMap = new HashMap<>();
    private String currentMonthDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        tvCurrentMonth = findViewById(R.id.tvCurrentMonth);
        tvEmptyMessage = findViewById(R.id.tvEmptyMessage);
        gridCalendar = findViewById(R.id.gridCalendar);

        databaseHelper = new RoomDatabaseHelper(this);

        updateCalendar(false); // Cargar calendario inicial
        findViewById(R.id.btnPrevMonth).setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, -1);
            updateCalendar(true); // Cambiar a mes anterior
        });

        findViewById(R.id.btnNextMonth).setOnClickListener(v -> {
            calendar.add(Calendar.MONTH, 1);
            updateCalendar(true); // Cambiar a mes siguiente
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCalendar(false); // Actualizar solo si hay cambios
    }

    private void updateCalendar(boolean forceRedraw) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        String newMonthDisplayed = sdf.format(calendar.getTime());

        // Evitar redibujar si el mes no ha cambiado
        if (!forceRedraw && newMonthDisplayed.equals(currentMonthDisplayed)) {
            return;
        }
        currentMonthDisplayed = newMonthDisplayed;
        tvCurrentMonth.setText(currentMonthDisplayed);

        gridCalendar.removeAllViews(); // Limpiar el calendario para redibujar
        Calendar tempCalendar = (Calendar) calendar.clone();
        tempCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK) - 1; // Ajuste para empezar desde el día de la semana
        int daysInMonth = tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        Executors.newSingleThreadExecutor().execute(() -> {
            List<Actividad> actividades = databaseHelper.getAllActivities();
            dayActivityMap = mapActivitiesToDays(actividades);

            runOnUiThread(() -> {
                // Añadir celdas vacías para días antes del comienzo del mes
                for (int i = 0; i < firstDayOfWeek; i++) {
                    addEmptyCell();
                }

                // Dibujar los días del mes con actividades
                for (int day = 1; day <= daysInMonth; day++) {
                    Actividad actividad = dayActivityMap.get(day);
                    addDayCell(day, actividad);
                }

                // Mostrar mensaje si no hay actividades
                boolean hasActivities = !dayActivityMap.isEmpty();
                tvEmptyMessage.setVisibility(hasActivities ? View.GONE : View.VISIBLE);
            });
        });
    }

    private Map<Integer, Actividad> mapActivitiesToDays(List<Actividad> actividades) {
        Map<Integer, Actividad> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        for (Actividad actividad : actividades) {
            try {
                Calendar activityCalendar = Calendar.getInstance();
                activityCalendar.setTime(sdf.parse(actividad.getFecha()));
                if (activityCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                        activityCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                    int dayOfMonth = activityCalendar.get(Calendar.DAY_OF_MONTH);
                    map.put(dayOfMonth, actividad);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private void addEmptyCell() {
        TextView textView = new TextView(this);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        textView.setLayoutParams(params);
        gridCalendar.addView(textView);
    }

    private void addDayCell(int day, Actividad actividad) {
        TextView textView = new TextView(this);
        textView.setText(String.valueOf(day));
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(14);
        textView.setPadding(16, 16, 16, 16);

        if (actividad != null) {
            textView.setBackgroundColor(Color.parseColor("#FFBB86FC"));
            textView.setTextColor(Color.WHITE);
            textView.setOnClickListener(v -> {
                Toast.makeText(this,
                        "Asignatura: " + actividad.getAsignatura() + "\nHora: " + actividad.getHora(),
                        Toast.LENGTH_LONG).show();
            });
        } else {
            textView.setTextColor(Color.BLACK);
        }

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(8, 8, 8, 8);

        textView.setLayoutParams(params);
        gridCalendar.addView(textView);
    }
}
