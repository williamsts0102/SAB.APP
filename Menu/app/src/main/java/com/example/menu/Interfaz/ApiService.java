package com.example.menu.Interfaz;

import com.example.menu.Models.Alerta;
import com.example.menu.Models.PersonalRequest;
import com.example.menu.RequestModel;
import com.example.menu.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("api/SAB/ObtenerAlertaPorPersonal")
    Call<ResponseModel> sendRequest(@Body RequestModel requestModel);
}