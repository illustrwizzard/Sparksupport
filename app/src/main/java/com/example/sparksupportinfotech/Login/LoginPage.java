package com.example.sparksupportinfotech.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sparksupportinfotech.R;
import com.example.sparksupportinfotech.Register.RegisterPage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPage extends AppCompatActivity {
    LoginUserViewModel loginUserViewModel;

    @Override
    public void onBackPressed() {

        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        TextView registerTextView = findViewById(R.id.registerTextView);
        loginUserViewModel=new ViewModelProvider(this).get(LoginUserViewModel.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();


                if (!username.isEmpty() & !password.isEmpty()) {

                    UserLogin userLogin=new UserLogin(username,password);

                    loginUserViewModel.loginUser(userLogin, new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()){
//                SharedPreferences sharedPreferences=getSharedPreferences("myPreef", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor=sharedPreferences.edit();
//                editor.putInt("check",1);
//                editor.apply();

                                Toast.makeText(LoginPage.this, "Login Success full", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(LoginPage.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                            Toast.makeText(LoginPage.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                    // Successful login
                    Toast.makeText(LoginPage.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    // Failed login
                    Toast.makeText(LoginPage.this, "Field Cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginPage.this, RegisterPage.class);
                startActivity(i);
            }
        });
    }


}