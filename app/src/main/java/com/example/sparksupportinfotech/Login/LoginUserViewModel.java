package com.example.sparksupportinfotech.Login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sparksupportinfotech.ApiCall;
import com.example.sparksupportinfotech.Register.RetrofitClient;

import retrofit2.Callback;

import retrofit2.Call;
import retrofit2.Response;

public class LoginUserViewModel extends ViewModel {
    private LoginRepository loginRepository;

    // LiveData for UI updates
    private final MutableLiveData<String> successMessage = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LoginUserViewModel() {
        loginRepository=new LoginRepository();
    }

  public void login(String username,String password){
        isLoading.setValue(true);

        loginRepository.login(username, password, new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {

                if (response.isSuccessful()) {
                    successMessage.setValue("Login successful!");
                } else {
                    errorMessage.setValue("Login failed. Please try again.");
                }

            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {

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
