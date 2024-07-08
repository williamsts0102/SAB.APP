// SettingsFragment.java
package com.example.menu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.menu.Interfaz.ApiService;
import com.example.menu.RequestModel;
import com.example.menu.ResponseModel;
import com.example.menu.Models.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    private TextView codAlertaTextView;
    private TextView departamentoTextView;
    private TextView provinciaTextView;
    private TextView distritoTextView;
    private TextView direccionTextView;
    private TextView latitudTextView;
    private TextView longitudTextView;
    private TextView descripcionTextView;
    private TextView nombreGrupoPersonalTextView;
    private TextView noDataTextView;
    private Button openMapsButton;

    private String latitud;
    private String longitud;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        // Inicializa tus vistas
        codAlertaTextView = view.findViewById(R.id.codAlertaTextView);
        departamentoTextView = view.findViewById(R.id.departamentoTextView);
        provinciaTextView = view.findViewById(R.id.provinciaTextView);
        distritoTextView = view.findViewById(R.id.distritoTextView);
        direccionTextView = view.findViewById(R.id.direccionTextView);
        latitudTextView = view.findViewById(R.id.latitudTextView);
        longitudTextView = view.findViewById(R.id.longitudTextView);
        descripcionTextView = view.findViewById(R.id.descripcionTextView);
        nombreGrupoPersonalTextView = view.findViewById(R.id.nombreGrupoPersonalTextView);
        noDataTextView = view.findViewById(R.id.noDataTextView);
        openMapsButton = view.findViewById(R.id.openMapsButton);

        // Establece el click listener para el botón
        openMapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleMaps();
            }
        });

        // Realiza la solicitud
        Retrofit retrofit = RetrofitClient.getClient("http://192.168.1.41:5134/");
        ApiService apiService = retrofit.create(ApiService.class);

        RequestModel requestModel = new RequestModel(0);

        Call<ResponseModel> call = apiService.sendRequest(requestModel);
        call.enqueue(new Callback<ResponseModel>() {

            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseModel responseModel = response.body();

                    if (responseModel.getStrCodAlerta() == null &&
                            responseModel.getStrDepartamento() == null &&
                            responseModel.getStrProvincia() == null &&
                            responseModel.getStrDistrito() == null &&
                            responseModel.getStrDireccion() == null &&
                            responseModel.getStrLatitud() == null &&
                            responseModel.getStrLongitud() == null &&
                            responseModel.getStrDescripcion() == null &&
                            responseModel.getStrNombreGrupoPersonal() == null) {

                        noDataTextView.setVisibility(View.VISIBLE);
                        noDataTextView.setText("No se encontraron datos");

                        // Oculta los otros TextView
                        codAlertaTextView.setVisibility(View.GONE);
                        departamentoTextView.setVisibility(View.GONE);
                        provinciaTextView.setVisibility(View.GONE);
                        distritoTextView.setVisibility(View.GONE);
                        direccionTextView.setVisibility(View.GONE);
                        latitudTextView.setVisibility(View.GONE);
                        longitudTextView.setVisibility(View.GONE);
                        descripcionTextView.setVisibility(View.GONE);
                        nombreGrupoPersonalTextView.setVisibility(View.GONE);
                        openMapsButton.setVisibility(View.GONE);
                    } else {
                        noDataTextView.setVisibility(View.GONE);

                        codAlertaTextView.setVisibility(View.VISIBLE);
                        departamentoTextView.setVisibility(View.VISIBLE);
                        provinciaTextView.setVisibility(View.VISIBLE);
                        distritoTextView.setVisibility(View.VISIBLE);
                        direccionTextView.setVisibility(View.VISIBLE);
                        latitudTextView.setVisibility(View.VISIBLE);
                        longitudTextView.setVisibility(View.VISIBLE);
                        descripcionTextView.setVisibility(View.VISIBLE);
                        nombreGrupoPersonalTextView.setVisibility(View.VISIBLE);
                        openMapsButton.setVisibility(View.VISIBLE);

                        codAlertaTextView.setText("Código de Alerta: " + responseModel.getStrCodAlerta());
                        departamentoTextView.setText("Departamento: " + responseModel.getStrDepartamento());
                        provinciaTextView.setText("Provincia: " + responseModel.getStrProvincia());
                        distritoTextView.setText("Distrito: " + responseModel.getStrDistrito());
                        direccionTextView.setText("Dirección: " + responseModel.getStrDireccion());
                        latitudTextView.setText("Latitud: " + responseModel.getStrLatitud());
                        longitudTextView.setText("Longitud: " + responseModel.getStrLongitud());
                        descripcionTextView.setText("Descripción: " + responseModel.getStrDescripcion());
                        nombreGrupoPersonalTextView.setText("Nombre del Grupo Personal: " + responseModel.getStrNombreGrupoPersonal());

                        latitud = responseModel.getStrLatitud();
                        longitud = responseModel.getStrLongitud();
                    }
                } else {
                    Toast.makeText(getActivity(), "No se encontraron datos", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                // Maneja errores de red u otros errores
                Toast.makeText(getActivity(), "Error en la solicitud: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    private void openGoogleMaps() {
        if (latitud != null && longitud != null) {
            String uri = "geo:" + latitud + "," + longitud + "?q=" + latitud + "," + longitud + "(Ubicación)";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setPackage("com.google.android.apps.maps");
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                // Si Google Maps no está instalado, abre en un navegador
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/search/?api=1&query=" + latitud + "," + longitud));
                startActivity(webIntent);
            }
        } else {
            Toast.makeText(getActivity(), "Coordenadas no disponibles", Toast.LENGTH_SHORT).show();
        }
    }
}
