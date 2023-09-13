package com.example.sparksupportinfotech.Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sparksupportinfotech.Login.LoginPage;
import com.example.sparksupportinfotech.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPage extends AppCompatActivity {
    RegisterUserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText firstNameEditText = findViewById(R.id.firstNameEditText);
        EditText lastNameEditText = findViewById(R.id.lastNameEditText);
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        EditText confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        Button registerButton = findViewById(R.id.registerButton);

        userViewModel= new ViewModelProvider(this).get(RegisterUserViewModel.class);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String email=emailEditText.getText().toString();
                String first_name=firstNameEditText.getText().toString();
                String last_name=lastNameEditText.getText().toString();
                String username=usernameEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                String password2=confirmPasswordEditText.getText().toString();

                if (!email.isEmpty()& !first_name.isEmpty() & !last_name.isEmpty() & !username.isEmpty() & !password.isEmpty() & !password2.isEmpty()){
                    if (password.equals(password2)){


                        UserRegister user = new UserRegister(email,first_name,last_name,username,password,password2);
                        userViewModel.signUpUser(user, new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    // Signup was successful, show a success message
                                    Intent intent=new Intent(RegisterPage.this, LoginPage.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(RegisterPage.this, "SignUp Failed", Toast.LENGTH_SHORT).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                // Network request failure, handle error
                                Toast.makeText(RegisterPage.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


                    }else {
                        Toast.makeText(RegisterPage.this, "The Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(RegisterPage.this, "Fields Cannot be empty", Toast.LENGTH_SHORT).show();

                }



            }
        });



    }
}