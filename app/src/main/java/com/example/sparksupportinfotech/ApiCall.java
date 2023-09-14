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
    Call<UserRegister> register(
            @Field("email") String email,
            @Field("first_name") String firstName,
            @Field("last_name") String lastName,
            @Field("username") String username,
            @Field("password") String password,
            @Field("password2") String confirmPassword

    );

    @FormUrlEncoded
    @POST("login/")
    Call<UserLogin> login(
            @Field("username") String username,
            @Field("password") String password
    );

}
