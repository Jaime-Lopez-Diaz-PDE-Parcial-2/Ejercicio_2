package com.example.ejercicio_2.ui.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejercicio_2.R;
import com.example.ejercicio_2.model.Actividad;
import com.example.ejercicio_2.repos.room.RoomDatabaseHelper;

public class AddActivity extends AppCompatActivity {

    private EditText etDay, etTime, etSubject;
    private Button btnSaveActivity;
    private RoomDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        databaseHelper = new RoomDatabaseHelper(this);

        etDay = findViewById(R.id.etDay);
        etTime = findViewById(R.id.etTime);
        etSubject = findViewById(R.id.etSubject);
        btnSaveActivity = findViewById(R.id.btnSaveActivity);

        btnSaveActivity.setOnClickListener(v -> saveActivity());
    }

    private void saveActivity() {
        String day = etDay.getText().toString().trim();
        String time = etTime.getText().toString().trim();
        String subject = etSubject.getText().toString().trim();

        if (day.isEmpty() || time.isEmpty() || subject.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar formato de fecha y hora
        if (!day.matches("\\d{4}-\\d{2}-\\d{2}")) {
            Toast.makeText(this, "Formato de fecha inválido. Use YYYY-MM-DD.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!time.matches("\\d{2}:\\d{2}")) {
            Toast.makeText(this, "Formato de hora inválido. Use HH:MM.", Toast.LENGTH_SHORT).show();
            return;
        }

        Actividad actividad = new Actividad(0, day, time, subject);
        databaseHelper.saveActivity(actividad);

        Toast.makeText(this, "Actividad guardada correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }

}
