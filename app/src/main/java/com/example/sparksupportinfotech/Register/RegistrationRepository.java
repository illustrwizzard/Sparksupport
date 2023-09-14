package com.example.sparksupportinfotech.Register;

import com.example.sparksupportinfotech.ApiCall;

import retrofit2.Call;
import retrofit2.Callback;

public class RegistrationRepository {

    private ApiCall apiCall;

    public RegistrationRepository() {

        apiCall = RetrofitClient.getApiService().create(ApiCall.class);
    }

    public void register(String email, String firstName, String lastName,String username, String password, String confirmPassword, Callback<UserRegister> callback) {

        Call<UserRegister> call = apiCall.register(email, firstName, lastName,username, password, confirmPassword);
        call.enqueue(callback);
    }
}
