package com.example.menu;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private EditText editTextDepartamento, editTextProvincia, editTextDistrito, editTextDireccion, editTextLatitud, editTextLongitud, editTextDescripcion;
    private Button btnEnviar;
    private RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inicializar componentes de la interfaz
        editTextDepartamento = view.findViewById(R.id.editTextDepartamento);
        editTextProvincia = view.findViewById(R.id.editTextProvincia);
        editTextDistrito = view.findViewById(R.id.editTextDistrito);
        editTextDireccion = view.findViewById(R.id.editTextDireccion);
        editTextLatitud = view.findViewById(R.id.editTextLatitud);
        editTextLongitud = view.findViewById(R.id.editTextLongitud);
        editTextDescripcion = view.findViewById(R.id.editTextDescripcion);
        btnEnviar = view.findViewById(R.id.btnEnviar);

        // Inicializar la cola de peticiones de Volley
        requestQueue = Volley.newRequestQueue(getContext());
        Log.d(TAG, "onCreateView: RequestQueue inicializada");

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Botón enviar presionado");
                enviarDatos();
            }
        });

        return view;
    }

    private void enviarDatos() {
        String url = "http://192.168.1.41:5134/api/SAB/RegistrarAlerta";
        Log.d(TAG, "enviarDatos: URL de la API: " + url);

        // Obtener los valores de los campos
        String departamento = editTextDepartamento.getText().toString();
        String provincia = editTextProvincia.getText().toString();
        String distrito = editTextDistrito.getText().toString();
        String direccion = editTextDireccion.getText().toString();
        String latitud = editTextLatitud.getText().toString();
        String longitud = editTextLongitud.getText().toString();
        String descripcion = editTextDescripcion.getText().toString();
        Log.d(TAG, "enviarDatos: Datos capturados - Departamento: " + departamento + ", Provincia: " + provincia + ", Distrito: " + distrito + ", Dirección: " + direccion + ", Latitud: " + latitud + ", Longitud: " + longitud + ", Descripción: " + descripcion);

        // Crear el objeto JSON con los datos
        JSONObject datos = new JSONObject();
        try {
            datos.put("pstrDepartamento", departamento);
            datos.put("pstrProvincia", provincia);
            datos.put("pstrDistrito", distrito);
            datos.put("pstrDireccion", direccion);
            datos.put("pstrLatitud", latitud);
            datos.put("pstrLongitud", longitud);
            datos.put("pstrDescripcion", descripcion);
            datos.put("pbitEstado", true);
            datos.put("pintIdUsuarioRegistro", 3);
            Log.d(TAG, "enviarDatos: JSON creado: " + datos.toString());
        } catch (JSONException e) {
            Log.e(TAG, "enviarDatos: Error al crear el JSON", e);
            e.printStackTrace();
        }

        // Crear la petición POST
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                datos,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Manejar la respuesta
                        Log.d(TAG, "onResponse: Respuesta de la API: " + response.toString());
                        Toast.makeText(getContext(), "Datos enviados correctamente", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error
                        Log.e(TAG, "onErrorResponse: Error al enviar los datos", error);
                        Toast.makeText(getContext(), "Error al enviar los datos", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Añadir la petición a la cola
        requestQueue.add(request);
        Log.d(TAG, "enviarDatos: Petición añadida a la cola");
    }
}
