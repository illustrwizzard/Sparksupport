package com.example.sparksupportinfotech.Register;

import com.example.sparksupportinfotech.ApiCall;

import retrofit2.Call;
import retrofit2.Callback;

public class RegistrationRepository {

    private ApiCall apiService;

    public RegistrationRepository() {
        apiService=RetrofitClient.getApiService().create(ApiCall.class);
    }

  public void registerUser(String username,String password,String password2,String email,String first_name,String last_name,Callback<UserRegister> callback){
        Call<UserRegister> call= apiService.registerUser(username,password,password2,email,first_name,last_name);
        call.enqueue(callback);
  }
}
