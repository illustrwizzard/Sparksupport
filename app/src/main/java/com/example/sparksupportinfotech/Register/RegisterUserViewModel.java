package com.example.sparksupportinfotech.Register;

import androidx.lifecycle.ViewModel;

import com.example.sparksupportinfotech.ApiCall;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterUserViewModel extends ViewModel {
    private ApiCall apiService;

    public RegisterUserViewModel() {

        apiService = RetrofitClient.getApiService();
    }

    public void signUpUser(UserRegister user, Callback<Void> callback) {

        Call<Void> call = apiService.signUp(user);
        call.enqueue(callback);
    }
}