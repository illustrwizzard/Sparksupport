package com.example.sparksupportinfotech.Login;

import androidx.lifecycle.ViewModel;

import com.example.sparksupportinfotech.ApiCall;
import com.example.sparksupportinfotech.Register.RetrofitClient;

import retrofit2.Callback;

import retrofit2.Call;

public class LoginUserViewModel extends ViewModel {
    private ApiCall apiCall;

    public LoginUserViewModel() {
        apiCall= RetrofitClient.getApiService();
    }

    public void loginUser(UserLogin userLogin, Callback<Void> callback){
        Call<Void> call=apiCall.loginIn(userLogin);
        call.enqueue(callback);
    }
}
