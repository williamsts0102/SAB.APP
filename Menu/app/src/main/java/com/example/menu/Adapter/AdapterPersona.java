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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_settings, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Alerta alerta = alertas.get(position);

        // Verificar si la lista está vacía
        if (alerta == null) {
            holder.itemView.setVisibility(View.GONE);
        } else {
            holder.itemView.setVisibility(View.VISIBLE);
            holder.textViewCodAlerta.setText(alerta.getStrCodAlerta());
            holder.textViewDescripcion.setText(alerta.getStrDescripcion());
            holder.textViewGrupoPersonal.setText(alerta.getStrNombreGrupoPersonal());
            holder.textViewDepartamento.setText(alerta.getStrDepartamento());
            holder.textViewProvincia.setText(alerta.getStrProvincia());
            holder.textViewDistrito.setText(alerta.getStrDistrito());
            holder.textViewDireccion.setText(alerta.getStrDireccion());
            holder.textViewLatitud.setText(alerta.getStrLatitud());
            holder.textViewLongitud.setText(alerta.getStrLongitud());
            // Set other views here
        }
    }

    @Override
    public int getItemCount() {
        return alertas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCodAlerta;
        TextView textViewDescripcion;
        TextView textViewGrupoPersonal;
        TextView textViewDepartamento;
        TextView textViewProvincia;
        TextView textViewDistrito;
        TextView textViewDireccion;
        TextView textViewLatitud;
        TextView textViewLongitud;
        // Define other views here

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCodAlerta = itemView.findViewById(R.id.text_view_cod_alerta);
            textViewDescripcion = itemView.findViewById(R.id.text_view_descripcion);
            // Initialize other views here
}
}
}