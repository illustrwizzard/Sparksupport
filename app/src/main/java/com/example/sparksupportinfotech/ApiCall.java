package com.example.sparksupportinfotech;

import com.example.sparksupportinfotech.DashBoard.DashboardResponse;
import com.example.sparksupportinfotech.Login.UserLogin;
import com.example.sparksupportinfotech.Register.UserRegister;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiCall {

    @FormUrlEncoded
    @POST("register/")
    Call<UserRegister> registerUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("password2") String password2,
            @Field("email") String email,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name
            );

//    @POST("registere/")
//    Call<Void> registerUsere(@Body UserRegister userRegistration);

    @FormUrlEncoded
    @POST("login/")
    Call<UserLogin> login(
            @Field("username") String username,
            @Field("password") String password
    );


    @GET("dashboard/")
    Call<List<DashboardResponse>> getImages(@Header("Authorization") String authorizationHeader);

}
