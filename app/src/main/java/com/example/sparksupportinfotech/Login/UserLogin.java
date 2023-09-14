package com.example.sparksupportinfotech.Login;

import com.google.gson.annotations.SerializedName;

public class UserLogin {
  @SerializedName("message")
    private String message;

  @SerializedName("tokken")
    private String tokken;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTokken() {
        return tokken;
    }

    public void setTokken(String tokken) {
        this.tokken = tokken;
    }
}
