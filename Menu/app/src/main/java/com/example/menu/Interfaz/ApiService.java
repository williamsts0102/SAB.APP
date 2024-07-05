package com.example.menu.Interfaz;

import com.example.menu.Models.Alerta;
import com.example.menu.Models.PersonalRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/SAB/ObtenerAlertaPorPersonal")
    Call<List<Alerta>> obtenerAlertasLista(@Body PersonalRequest personalRequest);
}
