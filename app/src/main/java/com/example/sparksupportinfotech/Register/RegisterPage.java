package com.example.sparksupportinfotech.Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sparksupportinfotech.DashBoard.DashBoardActivity;
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
        EditText usernameEditText = findViewById(R.id.usernameEditText1);
        EditText passwordEditText = findViewById(R.id.passwordEditText1);
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


                  userViewModel.registerUser(username,password,password2,email,first_name,last_name);

                       // userViewModel.registerUser("username551","password551","password551","email5551","first_name551","last_name551");


                    }else {
                        Toast.makeText(RegisterPage.this, "The Password is not matching", Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(RegisterPage.this, "Fields Cannot be empty", Toast.LENGTH_SHORT).show();

                }



            }
        });

        userViewModel.getSuccessMessage().observe(this,message->{
            Toast.makeText(this, "Registration Succesful", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(RegisterPage.this, LoginPage.class);
            startActivity(intent);
        });

        userViewModel.getErrorMessage().observe(this,messagee->{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        });

        userViewModel.isLoading().observe(this, isLoading -> {

        });



    }
}