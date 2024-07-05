package com.example.menu.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.example.menu.Models.Alerta;
import com.example.menu.R;

import java.util.ArrayList;
import java.util.List;


public class AdapterPersona extends RecyclerView.Adapter<AdapterPersona.ViewHolder> {

    private List<Alerta> alertas;

    public AdapterPersona(List<Alerta> alertas) {
        this.alertas = alertas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alerta, parent, false); // Asegúrate de tener un layout correcto para cada elemento de Alerta
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Alerta alerta = alertas.get(position);

        if (alerta != null) {
            holder.itemView.setVisibility(View.VISIBLE);
            holder.textViewCodAlerta.setText(alerta.getStrCodAlerta());
            holder.textViewDescripcion.setText(alerta.getStrDescripcion());
            // Asignar otros datos aquí
        } else {
            holder.itemView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return alertas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCodAlerta;
        TextView textViewDescripcion;
        // Otros TextViews

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCodAlerta = itemView.findViewById(R.id.text_view_cod_alerta); // Asegúrate de tener los IDs correctos
            textViewDescripcion = itemView.findViewById(R.id.text_view_descripcion); // IDs de los otros TextViews
        }
    }
}
