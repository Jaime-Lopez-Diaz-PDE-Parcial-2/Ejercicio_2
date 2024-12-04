package com.example.ejercicio_2.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejercicio_2.R;
import com.example.ejercicio_2.model.Actividad;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private Context context;
    private List<Actividad> actividades;

    public CalendarAdapter(Context context, List<Actividad> actividades) {
        this.context = context;
        this.actividades = actividades;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Actividad actividad = actividades.get(position);
        holder.tvDay.setText("Día: " + actividad.getFecha());
        holder.tvTime.setText("Hora: " + actividad.getHora());
        holder.tvSubject.setText("Asignatura: " + actividad.getAsignatura());
    }

    @Override
    public int getItemCount() {
        return actividades.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay, tvTime, tvSubject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.tvDay);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvSubject = itemView.findViewById(R.id.tvSubject);
        }
    }
}
