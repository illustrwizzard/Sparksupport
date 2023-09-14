package com.example.sparksupportinfotech.DashBoard;

import com.google.gson.annotations.SerializedName;

public class DashboardResponse {
    @SerializedName("id")
    private int id;

    @SerializedName("image_link")
    private String image_link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
}
