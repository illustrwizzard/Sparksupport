package com.example.sparksupportinfotech.VolleyRegisterPage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistrationViewModel extends AndroidViewModel {
    private MutableLiveData<String> registrationResponse = new MutableLiveData<>();
    private Application application;



    public RegistrationViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<String> getRegistrationResponse() {
        return registrationResponse;
    }

    public void registerUser(User user) {
        String url = "http://148.251.86.36:8001/register/";

        // Create a Volley request queue
        RequestQueue requestQueue = Volley.newRequestQueue(application);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the successful response here
                        registrationResponse.setValue(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle any errors that occurred during the request
                        registrationResponse.setValue("Error: " + error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                // Set the POST parameters
                Map<String, String> params = new HashMap<>();
                params.put("username", user.getUsername());
                params.put("password", user.getPassword());
                params.put("password2", user.getPassword2());
                params.put("email", user.getEmail());
                params.put("first_name", user.getFirst_name());
                params.put("last_name", user.getLast_name());
                return params;
            }
        };

        // Add the request to the request queue
        requestQueue.add(stringRequest);
    }
}