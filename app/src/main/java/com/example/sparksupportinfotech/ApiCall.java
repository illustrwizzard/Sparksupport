package com.example.sparksupportinfotech;

import com.example.sparksupportinfotech.Login.UserLogin;
import com.example.sparksupportinfotech.Register.UserRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiCall {

    @FormUrlEncoded
    @POST("register/")
    Call<Void> signUp(@Body UserRegister user);

    @FormUrlEncoded
    @POST("login/")
    Call<Void> loginIn(@Body UserLogin userLogin);

}
