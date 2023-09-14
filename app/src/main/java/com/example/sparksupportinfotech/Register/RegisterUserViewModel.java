package com.example.sparksupportinfotech.Register;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sparksupportinfotech.ApiCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserViewModel extends ViewModel {

    private  RegistrationRepository registrationRepository;
    private final MutableLiveData<String> successMessage = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();



    public RegisterUserViewModel() {
        // Initialize the registration repository
        registrationRepository = new RegistrationRepository();
    }

    public void registerUser(String username,String password,String password2,String email,String first_name,String last_name){
        isLoading.setValue(true);

        registrationRepository.registerUser(username,password,password2,email,first_name,last_name, new Callback<UserRegister>() {
            @Override
            public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
                if (response.isSuccessful()){
                    successMessage.setValue("Register Successful");
//                    UserRegister userRegister=response.body();
//                    String data=userRegister.getEmail();

                }else{
                    errorMessage.setValue("Registration failed. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<UserRegister> call, Throwable t) {

                Log.e("API Error", t.getMessage(), t);

                isLoading.setValue(false);
                errorMessage.setValue("Network error. Please check your connection.");

            }
        });
    }

    public LiveData<String> getSuccessMessage(){
        return successMessage;
    }

    public LiveData<String> getErrorMessage(){
        return errorMessage;
    }

    public LiveData<Boolean> isLoading(){
        return isLoading;
    }


}