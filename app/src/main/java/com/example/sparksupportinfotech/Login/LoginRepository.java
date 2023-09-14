package com.example.sparksupportinfotech.Login;

import com.example.sparksupportinfotech.ApiCall;
import com.example.sparksupportinfotech.Register.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginRepository {
    private ApiCall apiCall;

    public LoginRepository(){
        apiCall= RetrofitClient.getApiService().create(ApiCall.class);
    }
    public void login(String username, String password, Callback<UserLogin> callback){
        Call<UserLogin> call= apiCall.login(username, password);

        call.enqueue(callback);
    }
}
