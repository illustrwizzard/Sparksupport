package com.example.sparksupportinfotech.Register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sparksupportinfotech.ApiCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserViewModel extends ViewModel {

    private RegistrationRepository registrationRepository;

    // LiveData for UI updates
    private final MutableLiveData<String> successMessage = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    public RegisterUserViewModel() {

        registrationRepository = new RegistrationRepository();
    }

    public void register(String email, String firstName, String lastName,String username, String password, String confirmPassword) {
        isLoading.setValue(true);

        registrationRepository.register(email, firstName, lastName,username, password, confirmPassword,  new Callback<UserRegister>() {
            @Override
            public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
                isLoading.setValue(false);

                if (response.isSuccessful()) {
                    successMessage.setValue("Registration successful!");
                } else {
                    errorMessage.setValue("Registration failed. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<UserRegister> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Network error. Please check your connection.");
            }
        });
    }

    // Getter methods for LiveData
    public LiveData<String> getSuccessMessage() {
        return successMessage;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }
}