package com.example.sparksupportinfotech.DashBoard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sparksupportinfotech.ApiCall;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageViewModel extends AndroidViewModel {

    private ImageRepository imageRepository;
    private final MutableLiveData<List<DashboardResponse>> imageListLiveData = new MutableLiveData<>();
    public ImageViewModel(@NonNull Application application) {
        super(application);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://148.251.86.36:8001/") // Replace with your API endpoint
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiCall apiService = retrofit.create(ApiCall.class);

        // Initialize ImageRepository
        imageRepository = new ImageRepository(apiService);
    }
    public LiveData<List<DashboardResponse>> getImageList(String authToken) {
        // Fetch images from the repository and pass the auth token
        fetchImages(authToken);
        return imageListLiveData;
    }

    private void fetchImages(String authToken) {
        // Call the repository to fetch images
        Call<List<DashboardResponse>> call = imageRepository.getImages(authToken);

        call.enqueue(new Callback<List<DashboardResponse>>() {
            @Override
            public void onResponse(Call<List<DashboardResponse>> call, Response<List<DashboardResponse>> response) {
                if (response.isSuccessful()) {
                    // Update LiveData with the fetched images
                    imageListLiveData.setValue(response.body());
                } else {
                    // Handle API error
                    // You can log or show an error message here
                }
            }

            @Override
            public void onFailure(Call<List<DashboardResponse>> call, Throwable t) {
                // Handle network request failure
                // You can log or show an error message here
            }
        });
    }
}