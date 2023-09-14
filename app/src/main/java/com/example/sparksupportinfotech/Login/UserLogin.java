package com.example.sparksupportinfotech.Login;

import com.google.gson.annotations.SerializedName;

public class UserLogin {
  @SerializedName("refresh")
    private String refresh;

  @SerializedName("access")
    private String access;


    @SerializedName("username")
    private String username;

    @SerializedName("firstname")
    private String firstname;


    @SerializedName("lastname")
    private String lastname;


    @SerializedName("email")
    private String email;


  public UserLogin(String refresh, String access, String username, String firstname, String lastname, String email) {
    this.refresh = refresh;
    this.access = access;
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
  }

  public String getRefresh() {
    return refresh;
  }

  public void setRefresh(String refresh) {
    this.refresh = refresh;
  }

  public String getAccess() {
    return access;
  }

  public void setAccess(String access) {
    this.access = access;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
