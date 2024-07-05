package com.example.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu.Adapter.AdapterPersona;
import com.example.menu.Interfaz.ApiService;
import com.example.menu.Models.Alerta;
import com.example.menu.Models.PersonalRequest;
import com.example.menu.Models.RetrofitClient;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingsFragment extends Fragment {

    private static final String TAG = "SettingsFragment";
    private RecyclerView recyclerView;
    private AdapterPersona adapter;
    private ApiService apiService;
    private TextView textViewNoData;
    private LinearLayout alertDetailsLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        textViewNoData = view.findViewById(R.id.text_view_no_data);
        alertDetailsLayout = view.findViewById(R.id.alert_details_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        apiService = RetrofitClient.getClient("http://192.168.1.41:5134/").create(ApiService.class);
        fetchAlertas();

        return view;
    }

    private void fetchAlertas() {
        PersonalRequest personalRequest = new PersonalRequest(6);
        Call<List<Alerta>> call = apiService.obtenerAlertasLista(personalRequest); // Asumiendo un servicio que devuelve una lista de Alerta
        Log.d(TAG, "URL de la solicitud: " + call.request().url());

        call.enqueue(new Callback<List<Alerta>>() {
            @Override
            public void onResponse(Call<List<Alerta>> call, Response<List<Alerta>> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Alerta> alertas = response.body();

                        // Verificar si hay datos válidos en la lista de alertas recibida
                        if (alertas != null && !alertas.isEmpty()) {
                            adapter = new AdapterPersona(alertas);
                            recyclerView.setAdapter(adapter);
                            textViewNoData.setVisibility(View.GONE); // Ocultar el mensaje de "No hay datos"
                            recyclerView.setVisibility(View.VISIBLE); // Mostrar el RecyclerView
                            alertDetailsLayout.setVisibility(View.VISIBLE); // Mostrar detalles de la alerta (primera alerta)
                            updateAlertDetails(alertas.get(0)); // Actualizar detalles de la primera alerta
                        } else {
                            // No hay datos válidos
                            textViewNoData.setVisibility(View.VISIBLE); // Mostrar el mensaje de "No hay datos"
                            recyclerView.setVisibility(View.GONE); // Ocultar el RecyclerView
                            alertDetailsLayout.setVisibility(View.GONE); // Ocultar detalles de la alerta
                            Log.d(TAG, "No se encontraron datos válidos en la respuesta.");
                        }
                    } else {
                        // Manejar respuestas no exitosas
                        textViewNoData.setVisibility(View.VISIBLE); // Mostrar el mensaje de "No hay datos"
                        recyclerView.setVisibility(View.GONE); // Ocultar el RecyclerView
                        alertDetailsLayout.setVisibility(View.GONE); // Ocultar detalles de la alerta
                        Log.e(TAG, "Error en la respuesta: " + response.code());
                    }
                } catch (Exception e) {
                    textViewNoData.setVisibility(View.VISIBLE); // Mostrar el mensaje de "No hay datos"
                    recyclerView.setVisibility(View.GONE); // Ocultar el RecyclerView
                    alertDetailsLayout.setVisibility(View.GONE); // Ocultar detalles de la alerta
                    Log.e(TAG, "Error procesando la respuesta: " + e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Alerta>> call, Throwable t) {
                textViewNoData.setVisibility(View.VISIBLE); // Mostrar el mensaje de "No hay datos"
                recyclerView.setVisibility(View.GONE); // Ocultar el RecyclerView
                alertDetailsLayout.setVisibility(View.GONE); // Ocultar detalles de la alerta
                // Manejar fallos en la llamada
                Log.e(TAG, "Error en la llamada: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    // Método para actualizar los detalles de la alerta en los TextViews correspondientes
    private void updateAlertDetails(Alerta alerta) {
        TextView codAlerta = getView().findViewById(R.id.text_view_cod_alerta);
        TextView departamento = getView().findViewById(R.id.text_view_grupo_departamento);
        TextView provincia = getView().findViewById(R.id.text_view_grupo_provincia);
        TextView distrito = getView().findViewById(R.id.text_view_grupo_distrito);
        TextView direccion = getView().findViewById(R.id.text_view_grupo_direccion);
        TextView descripcion = getView().findViewById(R.id.text_view_descripcion);
        TextView grupoPersonal = getView().findViewById(R.id.text_view_grupo_personal);
        TextView latitud = getView().findViewById(R.id.text_view_grupo_latitud);
        TextView longitud = getView().findViewById(R.id.text_view_grupo_longitud);

        codAlerta.setText(alerta.getStrCodAlerta());
        departamento.setText(alerta.getStrDepartamento());
        provincia.setText(alerta.getStrProvincia());
        distrito.setText(alerta.getStrDistrito());
        direccion.setText(alerta.getStrDireccion());
        descripcion.setText(alerta.getStrDescripcion());
        grupoPersonal.setText(alerta.getStrNombreGrupoPersonal());
        latitud.setText(alerta.getStrLatitud());
        longitud.setText(alerta.getStrLongitud());
    }}
