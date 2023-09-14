package com.example.sparksupportinfotech.DashBoard;

import com.example.sparksupportinfotech.ApiCall;

import java.util.List;

import retrofit2.Call;

public class ImageRepository {

    private final ApiCall apiService;

    public ImageRepository(ApiCall apiService) {
        this.apiService = apiService;
    }

    public Call<List<DashboardResponse>> getImages(String authToken) {
        // Make the API call to fetch images with the provided authorization token
        return apiService.getImages("Bearer " + authToken);
    }
}
